package com.gac.xs6.bigdata.core

import com.gac.xs6.bigdata.model._
import org.apache.spark.streaming.dstream.DStream

/**
  * 数据适配
  */
trait Adapter {
  /** *
    * 提取解析之后的数据
    *
    * @param stream （vin,"{'dVIN':'LHWCG64DXH1173730','dlongitude':'132.121213'...}"）
    * @return (vin,OBDData)
    */
  def extract(stream: DStream[Option[(String,  String)]]):DStream[(String, SourceData)]

  /**
    * 解析之后，并去除了偏移量
    * @param sourceDstream
    * @return
    */
  def removeOffset(sourceDstream:DStream[(String, SourceData)]):DStream[(String, SourceData)]

}

trait DataSanitizer {
  /***
    * 数据过滤清洗
    * @param stream (vin,OBDData)
    * @return （vin,SanitizedData）
    */
  def sanitize(stream: DStream[(String, SourceData)]): DStream[(String, SourceData)]
}


trait DrivingTripAggregator {
  /***
    * 行程统计计算，判断行程结束，行程结束把当前行程结果输出
    * @param stream DStream(vin,SourceData)
    * @return DStream(vin,TripState)
    */
  def aggregate(stream: DStream[(String, SourceData)]): DStream[(String, TripState)]
}





trait ChargeTripAggregator{
  /***
    * 充电划分计算，判断充电结束，充电结束把当前充电结果输出
    * @param stream DStream(vin,SourceData)
    * @return DStream(vin,ChargeState)
    */
  def aggregate(stream: DStream[(String, SourceData)]): DStream[(String, ChargeState)]
}

trait EventsChecker {
  /**
    * 事件检测
    * @param stream
    * @return
    */
  def check(stream: DStream[(String, SourceData)]): DStream[(String, Event)]
}

trait Latest {
  /**
    * 存 Latest 最新一条数据
    * @param stream
    * @return
    */
  def getLatest(stream: DStream[(String, SourceData)]): DStream[(String, SourceData)]
}

