package com.gac.xs6.bigdata

import javax.inject.{Inject, Singleton}
import com.datastax.spark.connector.util.Logging
import com.google.inject.Guice
import com.typesafe.config.ConfigFactory
import com.gac.xs6.bigdata.BigdataApplication.Params
import com.gac.xs6.bigdata.conf.SparkConfiguration
import com.gac.xs6.bigdata.core.{Adapter, SubTrip}
import com.gac.xs6.bigdata.model.SourceData
import com.gac.xs6.bigdata.module.MainModule
import com.gac.xs6.bigdata.pipeline.DStreamSource
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import scopt.OptionParser
import org.apache.spark.SparkContext

object BigdataApplication extends App with Logging {
  logInfo("socket streaming started")

  val parser = new OptionParser[Params]("BigdataApplication") {
    head("socket streaming")

    opt[String]('c', "com/xs/telematics/conf")
      .text("config.resource for gac")
      .action((x, c) => c.copy(conf = x))

    help("help").text("prints this usage text")
  }

  parser.parse(args, Params()) match {
    case Some(params) =>
      val injector = Guice.createInjector(MainModule)
      val runner = injector.getInstance(classOf[BigdataApplication])
      ConfigFactory.invalidateCaches()
      runner.run(params)
    case _ => sys.exit(1)
  }

  case class Params(conf: String = "application.conf")

}

@Singleton
class BigdataApplication @Inject()(sparkConf: SparkConfiguration,
                                   sparkContext: SparkContext,
                                   source: DStreamSource[String],
                                   adapter: Adapter,
                                   trip: SubTrip
                                  ) extends Serializable with Logging {

  private def createNewStreamingContext: StreamingContext = {
    val ssc = new StreamingContext(sparkContext = sparkContext, Seconds(30))
    ssc.checkpoint(sparkConf.checkPointPath)

    sys.addShutdownHook {
      logInfo("Gracefully stopping Spark Streaming Application")
      ssc.stop(stopSparkContext = true, stopGracefully = true)
      logInfo("Application stopped")
    }

    val dStream: DStream[String] = source.stream(ssc)
    val sourceDstream: DStream[(String,SourceData)] = adapter.extract(dStream)

    val subTrip = trip.aggregate(sourceDstream)
    subTrip.print()
    ssc
  }

  def run(params: Params): Unit = {

    val ssc = StreamingContext.getOrCreate(sparkConf.checkPointPath, () => createNewStreamingContext)

    ssc.start()
    ssc.awaitTermination()
  }
}