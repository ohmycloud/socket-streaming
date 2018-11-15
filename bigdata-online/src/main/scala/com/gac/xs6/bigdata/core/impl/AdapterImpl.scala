package com.gac.xs6.bigdata.core.impl

import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}
import com.datastax.spark.connector.util.Logging
import com.gac.xs6.bigdata.core.Adapter
import com.gac.xs6.bigdata.model.{QualiferData, SourceData}
import com.gac.xs6.bigdata.util.GPSUtil
import org.apache.spark.streaming.Duration
import org.apache.spark.streaming.dstream.DStream
import scala.collection.JavaConversions._
import scala.collection.mutable

/**
  * 适配器, 接入kafka数据，对数据内容格式转换解析
  */
object AdapterImpl extends Adapter with Logging {
  val dataTimeout: Duration = Duration(15 * 1000)

  def toArrayString(jSONArray: JSONArray):String={
    if(null!=jSONArray){
      jSONArray.toString
    }else{
      ""
    }
  }

  /** *
    * 提取解析之后的数据
    *
    * @param stream （vin,"{'dVIN':'LHWCG64DXH1173730','dlongitude':'132.121213'...}"）
    * @return (vin,OBDData)
    */
  override def  extract(stream: DStream[Option[(String, String)]]): DStream[(String, SourceData)] = {
    val eventMaps: mutable.HashMap[String, String]=scala.collection.mutable.HashMap()
    val singal30sMaps: mutable.HashMap[String, String]=scala.collection.mutable.HashMap()
    val singal1sMaps: mutable.HashMap[String, String]=scala.collection.mutable.HashMap()
    synchronized {
      stream.map { result =>
        val c = result.getOrElse(null)
        if (null != c && null != c._2) {
          // println(c._2)
          try {
            val data = JSON.parseObject(c._2)

            val vin = data.getString("vin")
            val version = data.getString("version")
            val tboxSn = data.getString("tboxSn")
            val createTime = data.getTimestamp("createTime")
            val iccid = data.getString("iccid")

            //所有事件数据 解析放map
            var event = data.getJSONObject("event").getJSONObject("info")
            if (event == null) {
              event = JSON.parseObject("{}")
            }
            eventMaps.clear()
            for (key <- event.keySet()) {
              val jSONObject:JSONObject=event.getJSONObject(key)
              if(null!=jSONObject && !jSONObject.isEmpty){
                eventMaps.put(key,jSONObject.toString)
              }
            }

            //所有1s信号数据 解析放map
            var signal1s = data.getJSONObject("signal1s").getJSONObject("info")
            if (signal1s == null) {
              signal1s = JSON.parseObject("{}")
            }
            singal1sMaps.clear()
            for (key <- signal1s.keySet()) {
              val jSONArray: JSONArray=signal1s.getJSONArray(key)
              if(null!=jSONArray){
                singal1sMaps.put(key,jSONArray.toString)
              }
            }
            var tmp = 0
            var last_meter:Int=0
            val array_meter = signal1s.getJSONArray(QualiferData.ICU_ICUTotalOdometer)
            if (array_meter != null && array_meter.size() > 0) {
              var flag=true
              //逆序遍历
              for(i <- (3 until array_meter.length).reverse if flag){
                last_meter =array_meter.getInteger(i)
                tmp=array_meter.getInteger(i-3)
                if((tmp-last_meter>50) || (last_meter-tmp>50)){
                  flag=true
                }else{
                  //说明相邻的数字是正确的  退出
                  flag=false
                }
              }
           //   println(tmp)
            }

            //所有30s信号数据  解析放map
            var signal30s = data.getJSONObject("signal30s").getJSONObject("info")
            if (signal30s == null) {
              signal30s = JSON.parseObject("{}")
            }
            singal30sMaps.clear()
            for (key <- signal30s.keySet()) {
              val jSONArray:JSONArray=signal30s.getJSONArray(key)
              if(null!=jSONArray && jSONArray.size()>0){
                singal30sMaps.put(key,jSONArray.getString(0))
              }
            }

            /*  val BMS_CellVol: String = toArrayString(signal30s.getJSONArray("BMS_CellVol"))
          val BMS_TempProbe: String = toArrayString(signal30s.getJSONArray("BMS_TempProbe"))*/

            val arrayLatitude = signal30s.getJSONArray(QualiferData.GPS_Latitude)
            var GPS_Latitude: String = null
            if (arrayLatitude != null && arrayLatitude.size() > 0) {
              GPS_Latitude = String.valueOf(BigDecimal.apply(arrayLatitude.getLong(0)).*(BigDecimal.apply("0.000001")))
            }
            val arrayLongitude = signal30s.getJSONArray(QualiferData.GPS_Longitude)
            var GPS_Longitude: String = null
            if (arrayLongitude != null && arrayLongitude.size() > 0) {
              GPS_Longitude = String.valueOf(BigDecimal.apply(arrayLongitude.getLong(0)).*(BigDecimal.apply("0.000001")))
              val result = GPSUtil.gps84_To_Gcj02(GPS_Longitude.toDouble, GPS_Latitude.toDouble)
              GPS_Longitude = result._1
              GPS_Latitude = result._2
            }

            //singal30s.put("BMS_CellVol",BMS_CellVol)
            // singal30s.put("BMS_TempProbe",BMS_TempProbe)
            singal30sMaps.put(QualiferData.GPS_Latitude, GPS_Latitude)
            singal30sMaps.put(QualiferData.GPS_Longitude, GPS_Longitude)

            (c._1,SourceData(vin, version, tboxSn, createTime, iccid, last_meter, GPS_Latitude, GPS_Longitude, eventMaps, singal1sMaps, singal30sMaps))
          } catch {
            case e: Exception => println(e)
              println(c._2)
              null
          }
        } else {
          null
        }
      }
    }
  }


  /**
    * 解析之后，并去除了偏移量
    *
    * @param sourceDstream
    * @return
    */
  override def removeOffset(sourceDstream: DStream[(String, SourceData)]): DStream[(String, SourceData)] = ???
}