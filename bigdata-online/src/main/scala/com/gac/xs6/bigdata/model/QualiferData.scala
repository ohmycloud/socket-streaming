package com.gac.xs6.bigdata.model

/**
  * 字段映射
  */
object QualiferData {

  val vin="vin"
  val version="version"
  val tboxSn="tboxSn"
  val createTime="createTime"
  val iccid="iccid"
  val BMS_ACChrgAtclSta="BMS_ACChrgAtclSta"   //慢速充电
  val BMS_DCS_ActOprtMode="BMS_DCS_ActOprtMode"  //快速充电
  val BMS_ActlExchgCurnt="BMS_ActlExchgCurnt"   //电池实际输入输出电流_负为流入，正为流出
  val ESC_VehicleSpeed="ESC_VehicleSpeed"    //车辆速度
  val BMS_BatSOC_Actl="BMS_BatSOC_Actl"
  val BMS_BatSOC_Rpt="BMS_BatSOC_Rpt"
  val ICU_AverageVehicleSpeed="ICU_AverageVehicleSpeed"  //平均速度
  val ICU_DisplayVehicleSpeed="ICU_DisplayVehicleSpeed"
  val AC_IndoorTemp="AC_IndoorTemp"  //室内温度
  val AC_EnvirTemp="AC_EnvirTemp"  //环境温度

  val ICU_ICUTotalOdometer="ICU_ICUTotalOdometer"
  val GPS_Latitude="GPS_Latitude"
  val GPS_Longitude="GPS_Longitude"
  val GPS_Speed="GPS_Speed"
  val GPS_Heading="GPS_Heading"
  val BCM_BodyWarnSts="BCM_BodyWarnSts"  //防盗报警信号  2是报警
}
