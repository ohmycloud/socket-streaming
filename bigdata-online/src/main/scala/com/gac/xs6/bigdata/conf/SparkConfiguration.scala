package com.gac.xs6.bigdata.conf

import com.typesafe.config.ConfigFactory

/**
  * Spark 配置
  */
class SparkConfiguration {
  private val config = ConfigFactory.load()
  lazy val sparkConf = config.getConfig("spark")
  lazy val sparkMaster = sparkConf.getString("master")
/*  lazy val sparkUIEnabled = sparkConf.getBoolean("ui.enabled")
  lazy val sparkStreamingBatchDuration = sparkConf.getLong("streaming.batch.duration")*/
  lazy val checkPointPath = sparkConf.getString("checkpoint.path")
  lazy val ttl = sparkConf.getString("spark.cleaner.ttl")
 // lazy val cleanCheckpoints = sparkConf.getString("spark.cleaner.referenceTracking.cleanCheckpoints")
}
