package com.gac.xs6.bigdata.dao

import java.text.SimpleDateFormat
import java.util.Date
import com.gac.xs6.bigdata.model.{ChargeState, SourceData, SpeedReduceData, TripState}
import org.apache.hadoop.hbase.client.{Put, Table}
import org.apache.hadoop.hbase.util.Bytes

/**
  * Auther: wkf
  * Date: 2018-08-13 17:24
  * Description: hbaseDao
  */
class HbaseSourceDao {
}
object HbaseSourceDao {


  //save dstream


  //put单条数据
  def saveData(sourceData: SourceData,table:Table): Unit ={
    try {
      val put=putSourceData(sourceData)
      if(null!=put){
          table.put(put)
      }
    } catch {
      case e: Exception =>
        // log error
        e.printStackTrace()
    }
  }
  //put多条数据
  def saveData(list: List[Put],table:Table): Unit ={
    try {
        import scala.collection.JavaConversions._
        table.put(list)
    } catch {
      case e: Exception =>
        // log error
        e.printStackTrace()
    }finally {
       if(null!=table)
             table.close()
    }
  }

  def putSourceData(d: SourceData):Put={

    val put = new Put(Bytes.toBytes(reverse(d.vin)+"_"+(Long.MaxValue - d.createTime.getTime))) // row key
    // column, qualifier, value
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("vin"), Bytes.toBytes(d.vin))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("version"), Bytes.toBytes(d.version))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("tboxSn"), Bytes.toBytes(d.tboxSn))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("createTime"), Bytes.toBytes(String.valueOf(d.createTime.getTime)))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("iccid"), Bytes.toBytes(d.iccid))
    //所有事件数据 put
    if(null!=d.eventMap && !d.eventMap.isEmpty){
      d.eventMap.foreach(event=>{
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes(event._1), Bytes.toBytes(event._2))
      })
    }
   //所有1s数据 put
    if(null!=d.singal1sMap && !d.singal1sMap.isEmpty){
      d.singal1sMap.foreach(data=>{
        put.addColumn(Bytes.toBytes("content"), Bytes.toBytes(data._1), Bytes.toBytes(data._2))
      })
    }
   //所有30s数据put
    if(null!=d.singal30sMap && !d.singal30sMap.isEmpty){
      d.singal30sMap.foreach(event=>{
        put.addColumn(Bytes.toBytes("content"), Bytes.toBytes(event._1), Bytes.toBytes(event._2))
      })
    }

    put
  }


  def putSpeedData(d: SpeedReduceData):Put={

    val put =new  Put(Bytes.toBytes(reverse(d.vin))) // row key
    // column, qualifier, value

    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("vin"), Bytes.toBytes(d.vin))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("carTime"), Bytes.toBytes(String.valueOf(d.carTime)))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("GPS_Latitude"), Bytes.toBytes(d.GPS_Latitude))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("GPS_Longitude"), Bytes.toBytes(d.GPS_Longitude))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("last_meter"), Bytes.toBytes(String.valueOf(d.last_meter)))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("BMS_DCS_ActlChrgCurrent"), Bytes.toBytes(d.BMS_DCS_ActlChrgCurrent))

    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("AC_EnvirTemp"), Bytes.toBytes(d.AC_EnvirTemp))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("AC_IndoorTemp"), Bytes.toBytes(d.AC_IndoorTemp))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("BMS_BatSOH"), Bytes.toBytes(d.BMS_BatSOH))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("TPMS_PressureValue_FL"), Bytes.toBytes(d.TPMS_PressureValue_FL))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("TPMS_PressureValue_FR"), Bytes.toBytes(d.TPMS_PressureValue_FR))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("TPMS_PressureValue_RL"), Bytes.toBytes(d.TPMS_PressureValue_RL))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("TPMS_PressureValue_RR"), Bytes.toBytes(d.TPMS_PressureValue_RR))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("VCU_VhclPwrCnsmpAvrg"), Bytes.toBytes(d.VCU_VhclPwrCnsmpAvrg))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("BMS_BatSOC_Rpt"), Bytes.toBytes(d.BMS_BatSOC_Rpt))
    //所有事件数据 put
    if(null!=d.eventSpeedMap && !d.eventSpeedMap.isEmpty){
      d.eventSpeedMap.foreach(event=>{
        put.addColumn(Bytes.toBytes("info"), Bytes.toBytes(event._1), Bytes.toBytes(event._2))
      })
      //d.eventSpeedMap.clear()
    }

    put
  }

  lazy val dateFormat = new SimpleDateFormat("yyyyMMddHHmmss")

  lazy val  df=new  java.text.DecimalFormat("#0.0")

  def putSourceTripData(d: TripState):Put={

    val put = new Put(Bytes.toBytes(reverse(d.id)+"_"+dateFormat.format(new Date(d.tripStartTime*1000L)))) // row key
    // column, qualifier, value
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("vin"), Bytes.toBytes(d.id))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("tripStartTime"), Bytes.toBytes(String.valueOf(d.tripStartTime)))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("tripEndTime"), Bytes.toBytes(String.valueOf(d.tripEndTime)))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("startMileage"), Bytes.toBytes(df.format(d.startMileage)))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("tripDistance"), Bytes.toBytes(df.format(d.tripDistance)))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("tripStatus"), Bytes.toBytes(d.tripStatus))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("coordinate"), Bytes.toBytes(d.coordinate.deleteCharAt(d.coordinate.length() - 1).toString))

    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("maxSpeed"), Bytes.toBytes(d.maxSpeed.toString))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("startSoc"), Bytes.toBytes(df.format(d.startSoc)))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("endSoc"), Bytes.toBytes(df.format(d.endSoc)))

    put
  }

  def putSourceChargeData(d: ChargeState):Put={


    val put = new Put(Bytes.toBytes(reverse(d.id)+"_"+dateFormat.format(new Date(d.carTime*1000L)))) // row key
    // column, qualifier, value
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("vin"), Bytes.toBytes(d.id))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("loopChargeNum"), Bytes.toBytes(String.valueOf(d.loopChargeNum)))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("isFastCharge"), Bytes.toBytes(String.valueOf(d.isFastCharge)))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("chargeStatus"), Bytes.toBytes(d.chargeStatus))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("coordinate"), Bytes.toBytes(d.coordinate.deleteCharAt(d.coordinate.length() - 1).toString))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("startSoc"), Bytes.toBytes(df.format(d.startSoc)))
    put.addColumn(Bytes.toBytes("info"),Bytes.toBytes("endSoc"), Bytes.toBytes(df.format(d.endSoc)))

    put
  }
  def reverse(value: String):String={
    new StringBuilder(value).reverse.toString()
  }


}
