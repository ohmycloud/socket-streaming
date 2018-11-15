package com.gac.xs6.bigdata.conf

import java.util.Properties
import com.typesafe.config.ConfigFactory

/**
  * Kafka 配置
  */
class KafkaConfiguration extends Serializable{
  private val config = ConfigFactory.load()

  lazy val kafkaConfig = config.getConfig("kafka")
  lazy val kafkaOBDDataTopic = kafkaConfig.getString("topic.obddata.name")
  lazy val kafkaOBDDataTopicPartitionNum = kafkaConfig.getInt("topic.obddata.partition.num")
  lazy val kafkaOBDDataTopicReplicationFactor = kafkaConfig.getInt("topic.obddata.replication.factor")
  lazy val kafkaZookeeperHost = kafkaConfig.getString("zookeeper.connect")
  lazy val kafkaAccidentEventTopic = kafkaConfig.getString("accident.topic.name")

  lazy val kafkaConsumerParam: Map[String, String] = Map[String, String](
    "bootstrap.servers" -> kafkaConfig.getString("bootstrap.servers")
  )
  lazy val kafkaConsumerParam2: Map[String, String] = Map(
    "bootstrap.servers" -> kafkaConfig.getString("bootstrap.servers"),
    "group.id" -> kafkaConfig.getString("group.id") ,
    "auto.offset.reset" -> "largest"
  )
}
