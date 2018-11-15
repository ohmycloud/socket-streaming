package com.gac.xs6.bigdata.core.impl

import nl.grons.metrics.scala.DefaultInstrumented
import org.apache.spark.streaming.dstream.DStream
import com.datastax.spark.connector.util.Logging
import com.gac.xs6.bigdata.core.DataSanitizer
import com.gac.xs6.bigdata.model._

/**
  * Auther: wkf
  * Date: 2018-08-13 17:24
  * Description: 数据清洗
  */
object DataSanitizerImpl extends DataSanitizer with DefaultInstrumented with Logging {



  /** *
    * 数据过滤清洗
    *
    * @param stream (vin,OBDData)
    * @return （vin,SanitizedData）
    */
  override def sanitize(stream: DStream[(String, SourceData)]): DStream[(String, SourceData)] ={
    stream
      .filter(isValidData)
  }

  /**
    * 该方法用于过滤不符合字段阈值的数据
    * @param obd
    * @return
    */
  def isValidData(obd: (String, SourceData)): Boolean = {
    try {
      if(null!=obd && null!=obd._2) {
     /*   obd._2 match {
         // case data if data.vin.length != 17 => false // 过滤vin.length!=17的数据
/*          case data if data.GPS_Latitude == null || "".equals(data.GPS_Latitude) || data.GPS_Latitude.toDouble.toInt <= 0 => false // 过滤GPS_Latitude的不合法数据
          case data if data.GPS_Longitude == null || "".equals(data.GPS_Longitude) || data.GPS_Longitude.toDouble.toInt <= 0 => false // 过滤GPS_Longitude的不合法数据*/
          case data if data.GPS_Latitude == null  => false // 过滤GPS_Latitude的不合法数据
          case data if data.GPS_Longitude == null => false // 过滤GPS_Longitude的不合法数据
          //case data if data.createTime.getTime <= 0 => false // 过滤carTime<=0的数据
          case _ => true
        }*/
        true
      }else{
        false
      }
    }catch {
        case e: Exception => println(e)
        true
    }
  }
}
