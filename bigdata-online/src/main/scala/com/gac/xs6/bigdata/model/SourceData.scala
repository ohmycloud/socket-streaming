package com.gac.xs6.bigdata.model

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import com.alibaba.fastjson.JSON
import scala.collection.mutable
/**
  * Auther: wkf
  * Date: 2018-08-13 17:24
  * Description: can信号数据字段
  */
case class SourceData
(
  vin:String="",
  version:String="",
  tboxSn:String="",
  createTime: Timestamp,
  iccid:String="",
  last_meter:Int,
  GPS_Latitude:String="",   //纬度
  GPS_Longitude:String="",  //经度
  val eventMap: mutable.HashMap[String, String]=scala.collection.mutable.HashMap(),
  val singal1sMap: mutable.HashMap[String, String]=scala.collection.mutable.HashMap(),
  val singal30sMap: mutable.HashMap[String, String]=scala.collection.mutable.HashMap()
)

object SourceData {

  def main(args: Array[String]): Unit = {

    val str="{\"createTime\":1536042510,\"event\":{\"frequency\":-1,\"info\":{\"BCM_HazardLampSts\":{\"diff_time\":[9,11,14,15,20,21,25,26,27],\"value\":[1,0,1,0,1,0,1,0,1]},\"BCM_TurnLampSta_Left\":{\"diff_time\":[0,1,3,5,7,9,10,12,14,15,16,18,20,21,23,25,26,28],\"value\":[1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0]},\"BCM_TurnLampSta_Right\":{\"diff_time\":[10,12,14,15,20,21],\"value\":[1,0,1,0,1,0]},\"SCM_TurnLightLeverSta\":{\"diff_time\":[9,11,14,15,20,21,25,26,27],\"value\":[0,1,0,1,0,1,0,1,0]}},\"startTime\":-1},\"iccid\":\"89860117750045564649\",\"signal1s\":{\"frequency\":1,\"info\":{\"APA_DistToparkslot\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"APA_Process_bar\":[127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127,127],\"BMS_ActlExchgCurnt\":[10006,10006,10006,10006,10006,10006,10006,10006,10006,10006,10006,10006,10006,10006,10006,10006,10008,10006,10005,10006,10006,10006,10006,10006,10006,10006,10006,10006,10005,10005],\"BMS_BatSOC_Actl\":[942,942,942,942,942,942,942,942,942,942,942,942,942,942,942,942,942,942,942,942,942,942,942,942,942,942,942,942,942,942],\"BMS_BatSOC_Rpt\":[984,984,984,984,984,984,984,984,984,984,984,984,984,984,984,984,984,984,984,984,984,984,984,984,984,984,984,984,984,984],\"BMS_IsoResistance\":[2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046,2046],\"ESC_VehicleSpeed\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"HU_TargetSOC\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"ICU_DisplayVehicleSpeed\":[-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"ICU_ICUTotalOdometer\":[-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1],\"LateralACC\":[50,52,51,51,51,51,51,51,52,51,51,50,51,53,51,51,51,51,52,51,52,51,52,51,51,51,51,52,51,50],\"LongitudeACC\":[-12,-11,-11,-12,-12,-11,-11,-13,-11,-12,-11,-10,-11,-12,-12,-12,-11,-12,-11,-12,-9,-11,-11,-12,-12,-12,-11,-12,-12,-11],\"SAS_Angle\":[32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767],\"SAS_RotSpeed\":[32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767,32767],\"VCU_AcclPedalPos\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"VCU_ActlMotorRotateSpd\":[10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000],\"VCU_Actl_MotorRotateSpd\":[10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000],\"VCU_Actl_MotorTorque\":[2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000],\"VCU_BrkPedalPos\":[6,6,6,5,5,5,5,6,6,5,5,6,5,5,5,6,5,6,6,5,6,5,5,5,5,5,5,5,5,5],\"VCU_DrvRangeDistEst\":[413,413,413,413,413,413,413,413,413,413,413,413,413,413,413,413,413,413,413,413,413,413,413,413,413,413,413,413,413,413],\"VCU_DrvReq_MotorTorq\":[2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000,2000],\"VCU_MotorActualPower\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"VCU_VhclPwrCnsmpActl\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"YRS_YawRate\":[18000,18005,18000,18000,18005,18000,18005,17995,18000,18000,17995,18000,18000,18000,18000,17995,17995,17995,18000,17995,17995,18000,18000,18000,17995,18000,17995,18005,18000,18000]},\"startTime\":0},\"signal30s\":{\"frequency\":0.3333333432674408,\"info\":{\"AC_ActTotalPower\":[0],\"AC_EnvirTemp\":[164],\"AC_HVHActlPwr_HVAC\":[0],\"AC_HVHDeviceInternTemp_HVAC\":[70],\"AC_InAirPM25Value\":[18],\"AC_IndoorTemp\":[182],\"AC_OutAirQualityLevel\":[0],\"AC_RefrgHiPressure\":[0],\"AC_SeatHeatTemp_FL_Rsrv\":[0],\"AC_SeatHeatTemp_FR_Rsrv\":[0],\"BAT_HVHActlPwr\":[-1],\"BAT_HVHDeviceInternTemp\":[-1],\"BAT_HighVolDCTolalPwr\":[-1],\"BAT_PumpSpeedDutyRequest\":[-1],\"BMS_AuxHeatReqPower_Reserved\":[0],\"BMS_BatCapacity\":[153],\"BMS_BatSOH\":[1000],\"BMS_BatteryDTC_Num\":[0],\"BMS_BatteryDTC_list\":null,\"BMS_BatterySubSysCod\":[1],\"BMS_BatterySubSysNum\":[1],\"BMS_BatteryType\":[6],\"BMS_CellVol\":[[4175,4176,4178,4180,4180,4177,4178,4180,4185,4179,4179,4186,4183,4183,4184,4183,4182,4178,4183,4181,4179,4178,4176,4176,4174,4181,4178,4177,4178,4179,4181,4179,4172,4176,4175,4179,4175,4181,4181,4176,4175,4177,4178,4180,4178,4183,4177,4182,4175,4177,4175,4178,4175,4179,4179,4178,4178,4179,4176,4178,4176,4174,4176,4176,4176,4177,4174,4176,4178,4178,4177,4182,4178,4176,4179,4178,4181,4180,4179,4180,4180,4180,4176,4177,4176,4178,4177,4181,4179,4179,4180,4183,4180,4184,4181,4181,4187,4188,4189,4190,4181,4180,4182,4180]],\"BMS_CellVolAve\":[4179],\"BMS_CellVolMax\":[4190],\"BMS_CellVolMin\":[4172],\"BMS_ContactorTempAve_DCCharger\":[71],\"BMS_ContactorTempAve_Negative\":[76],\"BMS_ContactorTempAve_Positive\":[255],\"BMS_DCS_ActlChrgCurrent\":[0],\"BMS_DCS_ActlChrgPower\":[0],\"BMS_DCS_ActlChrgVoltage\":[0],\"BMS_DC_AC_RemChrgTime\":[0],\"BMS_ESS_InletColantActtemp\":[69],\"BMS_ESS_InletColanttargettemp\":[69],\"BMS_ESS_outletColantActtemp\":[69],\"BMS_HVBatActlVoltage\":[4344],\"BMS_HVBatCellTempAve\":[68],\"BMS_HVBatCellTempMax\":[69],\"BMS_HVBatCellTempMin\":[68],\"BMS_HVcppltnTempAve\":[68],\"BMS_HVcppltnTempMin\":[68],\"BMS_HVcppltnTempmax\":[69],\"BMS_MaxCellTemp\":[100],\"BMS_MaxCellVol\":[4280],\"BMS_MaxConDisCurnt\":[5000],\"BMS_MaxInstanDisCurnt\":[5000],\"BMS_MaxTempProbeCod\":[17],\"BMS_MaxVolCellCod\":[99],\"BMS_MinCellTemp\":[10],\"BMS_MinCellVol\":[2800],\"BMS_MinTempProbeCod\":[0],\"BMS_MinVolCellCod\":[32],\"BMS_NomCellCap\":[306],\"BMS_PwrRecupMaxConChrgCurnt\":[337],\"BMS_PwrRecupMaxInstanChrgCurnt\":[372],\"BMS_TempProbe\":[[88,88,88,88,88,88,88,88,88,88,88,88,88,88,88,88,88,88,89,89,88,89,88,88,88,88,89,89,89,89,89,89,88,89,88,89,88,88,89,88,89,89,89,89,88,89,88,88,88,88,89,89]],\"BMS_TotalCellNo\":[104],\"BMS_TotalTempProbe\":[52],\"GPS_Heading\":[27490],\"GPS_Latitude\":[30565834],\"GPS_Longitude\":[104186094],\"GPS_Speed\":[0],\"ICU_AverageVehicleSpeed\":[-1],\"ICU_ICUTripAOdometer\":[-1],\"ICU_ICUTripBOdometer\":[-1],\"ICU_WashLiquidLevelWarn\":[-1],\"ICU_drive_time\":[-1],\"PTG_DoorOpenRatio\":[-1],\"TPMS_PressureValue_FL\":[255],\"TPMS_PressureValue_FR\":[255],\"TPMS_PressureValue_RL\":[255],\"TPMS_PressureValue_RR\":[255],\"TPMS_TireTempValue_FL\":[255],\"TPMS_TireTempValue_FR\":[255],\"TPMS_TireTempValue_RL\":[255],\"TPMS_TireTempValue_RR\":[255],\"VCU_AtmosPressure\":[191],\"VCU_CruiseCtrTgtSpd\":[0],\"VCU_MCU_Input_Curr\":[1024],\"VCU_MCU_Input_Vol\":[866],\"VCU_MCU_Temp\":[70],\"VCU_MotorActualPower\":[0],\"VCU_MotorDTC_Num\":[0],\"VCU_MotorDTC_list\":null,\"VCU_MotorTemp\":[68],\"VCU_PCUInletCooltTemp\":[71],\"VCU_PwrCoolFanSpdDuty\":[1],\"VCU_VacuumPressure\":[133],\"VCU_VhclPwrCnsmpAvrg\":[112]},\"startTime\":0},\"tboxSn\":\"VE700459000J5H0029\",\"version\":\"0.1.0.0\",\"vin\":\"LL2274085JW100012\"}"
    val data = JSON.parseObject(str)
    var signal30s=data.getJSONObject("signal30s").getJSONObject("info")
    if (signal30s == null) {
      signal30s=JSON.parseObject("{}")
    }

//1535251163 |        1535255273
    val   df=new  java.text.DecimalFormat("#0.0")
    val a:Int =9834/10-1000
    //保留两位小数
    val b =  df.format(a)
    println(a )  //3.14
    if(a<(-17)){
      println(a )  //3.14
    }

    val arraySoc=JSON.parseArray("[0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]")
    var startSoc=200
    if(arraySoc!=null && arraySoc.size()>0){
      startSoc=arraySoc.getInteger(1)
    }
    lazy val dateFormat = new SimpleDateFormat("yyyyMMddHHmmss")
    println(dateFormat.format(new Date(1541093364*1000L)))
    println(dateFormat.format(new Date(1541064390000L)))
    //20181031184100  20181031131730
   val d:Long=(Long.MaxValue-9223372035313199581L)*1000L
    println(dateFormat.format(new Date(d)))
    println(dateFormat.format(new Date(1541093364*1000L)))

    val l:Long=(Long.MaxValue-1539574831)
    println(l)
    println(dateFormat.format(new Date(l)))
   // 20180925180200 20180925180630

    val start=dateFormat.parse("20181107000000").getTime()/1000
    val end=dateFormat.parse("20181107235959").getTime()/1000

    println(reverse("LL2274091JW110108")+"_"+(Long.MaxValue-start))

    println(reverse("LL2274099JW110566")+"_"+(Long.MaxValue-end))

    var last_meter=0
    var tmp=0
    var flag=true
    val array = Array(11179,11179,11179,11179,11179,11179,11179,11179,11179,11179,11179,11179,12,11179,11179,11179,11179,11179,11179,11179,11179,11179,11179,11179,11179,11159,12,12,12,11178)

    //逆序遍历
    for(i <- (3 until array.length).reverse if flag){
      last_meter =array(i)
      tmp=array(i-3)
      if((tmp-last_meter>50) || (last_meter-tmp>50)){
        flag=true
      }else{
        //说明相邻的数字是正确的  退出
        flag=false
      }
    }
    println(last_meter)
  }
  def reverse(value: String):String={
    new StringBuilder(value).reverse.toString()
  }
}


