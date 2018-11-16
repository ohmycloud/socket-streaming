package com.gac.xs6.bigdata

import javax.inject.{Inject, Singleton}
import com.datastax.spark.connector.util.Logging
import com.google.inject.Guice
import com.typesafe.config.ConfigFactory
import com.gac.xs6.bigdata.BigdataApplication.Params
import com.gac.xs6.bigdata.conf.{KafkaConfiguration, SparkConfiguration}
import com.gac.xs6.bigdata.core._
import com.gac.xs6.bigdata.dao.{HbaseSourceDao, HbaseUtil, MysqlSourceDao, MysqlUtil}
import com.gac.xs6.bigdata.model.{ChargeState, SourceData, TripState}
import com.gac.xs6.bigdata.module.MainModule
import com.gac.xs6.bigdata.pipeline.DStreamSource
import org.apache.hadoop.hbase.TableName
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}
import scopt.OptionParser
import org.apache.spark.SparkContext

/**
  * Auther: wkf
  * Date: 2018-07-12 09:25
  * Description: 入口类
  */
object BigdataApplication extends App with Logging {
  logInfo("Telematics started")

  val parser = new OptionParser[Params]("KafkaFeeder") {
    head("KafkaFeeder: obddata streaming from file")

    opt[String]('c', "com/xs/telematics/conf")
      .text("config.resource for telematics")
      .action((x, c) => c.copy(conf = x))

    help("help").text("prints this usage text")
  }

  parser.parse(args, Params()) match {
    case Some(params) =>
      val injector = Guice.createInjector(MainModule)
      val runner = injector.getInstance(classOf[BigdataApplication])
      ConfigFactory.invalidateCaches()
      //System.setProperty("config.file", params.conf)
      runner.run(params)
    case _ => sys.exit(1)
  }

  case class Params(conf: String = "application.conf")

}

@Singleton
class BigdataApplication @Inject()(sparkConf: SparkConfiguration,
                                   sparkContext: SparkContext,
                                   source: DStreamSource[Option[(String, String)]],
                                   adapter: Adapter,
                                   sanitizer: DataSanitizer,
                                   drivingTrip: DrivingTripAggregator,
                                   chargeTrip: ChargeTripAggregator,
                                   events: EventsChecker,
                                   latest: Latest,
                                   kafkaConf: KafkaConfiguration
                                  ) extends Serializable with Logging {

  private def createNewStreamingContext: StreamingContext = {
    val ssc = new StreamingContext(sparkContext = sparkContext, Seconds(30))

    ssc.checkpoint(sparkConf.checkPointPath)
    sys.addShutdownHook {
      logInfo("Gracefully stopping Spark Streaming Application")
      ssc.stop(stopSparkContext = true, stopGracefully = true)
      logInfo("Application stopped")
    }

    val dStream: DStream[Option[(String, String)]] = source.stream(ssc)
//    dStream.saveAsTextFiles("/tmp/hbase/data/")
    val sourceDstream: DStream[(String, SourceData)] = adapter.extract(dStream)
//    if(null!=sourceDstream){
      //数据过滤
      val dataSanitizer: DStream[(String, SourceData)] =sanitizer.sanitize(sourceDstream)
      //过滤后数据持久化
      dataSanitizer.persist(StorageLevel.MEMORY_ONLY_SER)

      //数据持久化后定时checkpoint
      dataSanitizer.checkpoint(new Duration(180000))

      //can信号数据存储
      saveCanSignal(dataSanitizer)

      //get latest data
      latest.getLatest(dataSanitizer)
      //save latest data

      //行程划分
      val drivingTripResult=drivingTrip.aggregate(dataSanitizer)
      drivingTripResult.persist(StorageLevel.MEMORY_ONLY_SER)
      //划分结果保存hbase
      saveDrivingTrip(drivingTripResult)

      //充电划分
      val chargeTripResult=chargeTrip.aggregate(dataSanitizer)
      //划分结果保存mysql
      saveChargeTrip(chargeTripResult)


      //event check
    events.check(dataSanitizer)
    //save events

//    }
    ssc
  }

  def saveDrivingTrip(drivingTripResult: DStream[(String, TripState)]): Unit = {
    drivingTripResult.foreachRDD(rdd => {
      rdd.foreachPartition(partitionRdd => {
        val connection = HbaseUtil.getHbaseConn // 获取Hbase连接
        val tableName = TableName.valueOf("trip_signal")
        val table = connection.getTable(tableName)
        val list = partitionRdd.map(data => {
          HbaseSourceDao.putSourceTripData(data._2)
        }).toList
        if (null != list && list.length > 0) {
          HbaseSourceDao.saveData(list, table)
        }
        //         //关闭Hbase连接
        HbaseUtil.closeConnection(connection)
      })
    })
    //划分结果保存mysql
    drivingTripResult.foreachRDD(rdd => {
      rdd.foreachPartition(partitionRdd => {

        val conn = MysqlUtil.getConnection // 获取mysql连接
        val list = partitionRdd.map(data => {
          data._2
        }).toList
        MysqlSourceDao.saveTrip(conn, list)
        //         //关闭mysql连接
        MysqlUtil.closeConnection(conn)
      })
    })
  }

  def saveChargeTrip(chargeResult: DStream[(String, ChargeState)]): Unit = {
    chargeResult.foreachRDD(rdd => {
      rdd.foreachPartition(partitionRdd => {
        val connection = MysqlUtil.getConnection // 获取mysql连接
        val list = partitionRdd.map(data => {
          data._2
        }).toList
        if (null != list && list.length > 0) {
          MysqlSourceDao.saveCharge(connection, list)
        }
        //         //关闭mysql连接
        MysqlUtil.closeConnection(connection)
      })
    })
  }

  def saveCanSignal(dataSanitizer: DStream[(String, SourceData)]): Unit = {
    dataSanitizer.foreachRDD(rdd => {
      rdd.foreachPartition(partitionRdd => {
        val connection = HbaseUtil.getHbaseConn // 获取Hbase连接
        val tableName = TableName.valueOf("can_signal")
        val table = connection.getTable(tableName)
        val list = partitionRdd.map(data => {
          HbaseSourceDao.putSourceData(data._2)
        }).toList
        if (null != list && list.length > 0) {
          HbaseSourceDao.saveData(list, table)
        }
        //         //关闭Hbase连接
        HbaseUtil.closeConnection(connection)
      })
    })
  }

  def run(params: Params): Unit = {

    val ssc = StreamingContext.getOrCreate(sparkConf.checkPointPath, () => createNewStreamingContext)
    ssc.start()
    ssc.awaitTermination()
  }
}