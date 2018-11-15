package com.gac.xs6.bigdata.conf

import com.google.inject.Singleton
import com.typesafe.config.ConfigFactory

/**
  * HBase 配置
  */
@Singleton
class HbaseConfiguration {
  private val config  = ConfigFactory.load()
  lazy val cassConfig = config.getConfig("hbase")
  lazy val hbaseUrl   = cassConfig.getString("hbase.zookeeper.quorum")
  lazy val hbasePort  = cassConfig.getString("hbase.zookeeper.property.clientPort")
}
