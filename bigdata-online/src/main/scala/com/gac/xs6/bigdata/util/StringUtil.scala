package com.gac.xs6.bigdata.util

import com.alibaba.fastjson.{JSON, JSONArray}
import com.datastax.spark.connector.util.Logging
import com.gac.xs6.bigdata.model.{SourceData, SpeedReduceData, WaringData}
import scala.collection.mutable.ListBuffer

object StringUtil extends Logging{
  def getResultSpeed(list: List[SourceData],array: Array[String]): SpeedReduceData = {

    val s:SpeedReduceData= new SpeedReduceData()
    if(null!=list && list.length>0){
      synchronized {
        for (i <- 0 until list.length) {
          val sourceData: SourceData = list(i)

          val m = sourceData.eventMap
          for (j <- 0 until array.length) {
            val key: String = array(j)
            val value: String = m.get(key).getOrElse(null)
            if (null != value) {
              val ObjectMode = JSON.parseObject(value)
              if (null != ObjectMode) {
                val valueMode = ObjectMode.getJSONArray("value")
                if (valueMode != null && valueMode.size() > 0) {
                  s.eventSpeedMap.put(key, valueMode.getString(valueMode.size() - 1))
                }
              }
            }
          }

          if (i == list.length - 1) {
            //最后一条数据
            s.vin = sourceData.vin
            s.carTime=sourceData.createTime.getTime
            s.last_meter = sourceData.last_meter
            s.GPS_Latitude = sourceData.GPS_Latitude
            s.GPS_Longitude = sourceData.GPS_Longitude
            //  println(sourceData.singal30sMap)
            s.BMS_DCS_ActlChrgCurrent = sourceData.singal30sMap.get("BMS_DCS_ActlChrgCurrent").get
            s.AC_EnvirTemp = sourceData.singal30sMap.get("AC_EnvirTemp").get
            s.AC_IndoorTemp = sourceData.singal30sMap.get("AC_IndoorTemp").get
            s.BMS_BatSOH = sourceData.singal30sMap.get("BMS_BatSOH").get
            s.TPMS_PressureValue_FL = sourceData.singal30sMap.get("TPMS_PressureValue_FL").get
            s.TPMS_PressureValue_FR = sourceData.singal30sMap.get("TPMS_PressureValue_FR").get
            s.TPMS_PressureValue_RL = sourceData.singal30sMap.get("TPMS_PressureValue_RL").get
            s.TPMS_PressureValue_RR = sourceData.singal30sMap.get("TPMS_PressureValue_RR").get
            s.VCU_VhclPwrCnsmpAvrg = sourceData.singal30sMap.get("VCU_VhclPwrCnsmpAvrg").get
            s.BMS_BatSOC_Rpt = arrayToString(JSON.parseArray(sourceData.singal1sMap.get("BMS_BatSOC_Rpt").getOrElse("")))
          }
        }
      }
    }
    s
  }


  def arrayToString(jSONArray: JSONArray):String={
    var str:String=null
    if(null!=jSONArray && jSONArray.size()>0){
       str=jSONArray.getString(jSONArray.size()-1)
    }
    str
  }

  def getWaring(list: List[SourceData]):ListBuffer[WaringData]={
    if(null==list || list.length<=0){
      null
    }else {
      val res:ListBuffer[WaringData]=new ListBuffer[WaringData]()
      for (i <- 0 until list.length) {
        val d: SourceData = list(i)
        val result = d.eventMap.get("BCM_BodyWarnSts").getOrElse(null)
        if (null != result && !"".equals(result)) {
          val ObjectMode = JSON.parseObject(result)
          if (null != ObjectMode) {
            val valueMode = ObjectMode.getJSONArray("value")
            if (valueMode != null && valueMode.size() > 0) {
              for (i <- 0 until valueMode.size()) {
                val value = valueMode.getInteger(i)
                if (value == 2) {
                  val diff_time = ObjectMode.getJSONArray("diff_time")
                  val carTime = d.createTime.getTime + diff_time.getInteger(i)
                  res += new WaringData(d.vin, carTime, (d.GPS_Latitude + ":" + d.GPS_Longitude))
                }
              }
            }
          }
        }
      }
      res
    }
  }
}
