package com.gac.xs6.bigdata.dao

import com.typesafe.config.ConfigFactory
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory}
import org.apache.hadoop.hbase.{HBaseConfiguration, HConstants}
/**
  * Auther: wkf
  * Date: 2018-08-13 17:24
  * Description: hbase连接器
  */
object HbaseUtil extends Serializable {


  private val config = ConfigFactory.load()
  lazy val cassConfig = config.getConfig("hbase")

  lazy val hbaseUrl = cassConfig.getString("hbase.zookeeper.quorum")
  lazy val hbasePort = cassConfig.getString("hbase.zookeeper.property.clientPort")

  def getConfig():Configuration={
    synchronized {
      val conf = HBaseConfiguration.create()
      conf.set(HConstants.ZOOKEEPER_QUORUM, hbaseUrl)
      conf.set(HConstants.ZOOKEEPER_CLIENT_PORT, hbasePort)
      conf.set("hbase.defaults.for.version.skip", "true")
      conf
    }
  }

  /**
    * 获取Hbase连接
    *
    * @return
    */
  def  getHbaseConn(): Connection = {
    synchronized {
      val conf = HBaseConfiguration.create()
      conf.set(HConstants.ZOOKEEPER_QUORUM, hbaseUrl)
      conf.set(HConstants.ZOOKEEPER_CLIENT_PORT, hbasePort)
      // conf.set(HConstants.ZOOKEEPER_ZNODE_PARENT, zkParent)

      //val threadPool = Executors.newFixedThreadPool(10)
        ConnectionFactory.createConnection(conf)
    }
  }


  def closeConnection(connection:Connection)={
    if(null!=connection)
            connection.close()
  }
}