package com.gac.xs6.bigdata.module

import java.io.ByteArrayInputStream
import java.util.zip.GZIPInputStream

import com.datastax.spark.connector.util.Logging
import com.google.inject.{AbstractModule, Provides, Singleton}
import com.gac.xs6.bigdata.BigdataApplication
import com.gac.xs6.bigdata.conf.{HbaseConfiguration, KafkaConfiguration, SparkConfiguration}
import com.gac.xs6.bigdata.core._
import com.gac.xs6.bigdata.core.impl._
import com.gac.xs6.bigdata.pipeline.DStreamSource
import kafka.serializer.{DefaultDecoder, StringDecoder}
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka.KafkaUtils._
import org.apache.spark.{SparkConf, SparkContext}
import org.json4s.DefaultFormats
import com.gac.xs6.bigdata.util.Json4sHelper._
import org.apache.commons.io.IOUtils


/**
  * Auther: wkf
  * Date: 2018-08-2 12:24
  * Description:
  */
object MainModule extends AbstractModule with Logging {

  override def configure(): Unit = {
    logInfo("starting main injection module")
    bind(classOf[SparkConfiguration]).asEagerSingleton()
    bind(classOf[KafkaConfiguration]).asEagerSingleton()
    bind(classOf[HbaseConfiguration]).asEagerSingleton()

    bind(classOf[BigdataApplication])
    bind(classOf[Adapter]).toInstance(AdapterImpl)
    bind(classOf[DataSanitizer]).toInstance(DataSanitizerImpl)
    bind(classOf[DrivingTripAggregator]).toInstance(DrivingTripAggregatorImpl)
    bind(classOf[ChargeTripAggregator]).toInstance(ChargeTripAggregatorImpl)
    bind(classOf[EventsChecker]).toInstance(EventsCheckerImpl)
  }

  @Provides
  @Singleton
  def sparkContext(sparkConf: SparkConfiguration): SparkContext = {
    SparkContext.getOrCreate(new SparkConf()
      .setAppName("gac-xs6-bigdata-online")
      .setMaster(sparkConf.sparkMaster)
      .set("spark.cleaner.ttl", sparkConf.ttl) //显示生命定期清理RDD和checkpoint
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    )
  }

  @Provides
  @Singleton
  def kafkaOBDDataSource(kafkaConf: KafkaConfiguration): DStreamSource[Option[(String, String)]] = {
    new DStreamSource[Option[(String, String)]] {
      def stream(ssc: StreamingContext): DStream[Option[(String,  String)]] = {
        implicit val formats = DefaultFormats + StringToBigDecimal + StringToInt + StringToDouble + StringToInstant + StringToLong
        // TODO: check offset?
        val stream: DStream[(String,  Array[Byte])]=createDirectStream[String,  Array[Byte], StringDecoder, DefaultDecoder](ssc,
          kafkaParams = kafkaConf.kafkaConsumerParam2,
          topics = Set(kafkaConf.kafkaOBDDataTopic)
        )
        //kafka过来数据是tar包，需要解析tar包成json格式
        stream.map( record=> {
          val value: Array[Byte] = record._2
          val jsonValue: String = decompress(value)
          if (null == jsonValue || jsonValue.equals("")) {
                None
            } else {
               Some(record._1, jsonValue)
          }
        })
      }
    }

  }
  //解压gzip包   遇到解压异常 println异常 然后继续执行
  def decompress(array: Array[Byte]): String = {
    var data:String=null
    try {
        val bais = new ByteArrayInputStream(array)
        val gis: GZIPInputStream = new GZIPInputStream(bais)
        val result = new String(IOUtils.toByteArray(gis), "utf-8")
        data=result
        IOUtils.closeQuietly(bais)
        IOUtils.closeQuietly(gis)
    }catch {
      case e: Exception => println(e)
    }
    data
  }
}