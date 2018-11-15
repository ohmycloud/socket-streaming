package com.gac.xs6.bigdata.model

import scala.collection.mutable

class SpeedReduceData {
      var vin:String=""
      var carTime:Long=0 //汽车传入时间
      var last_meter:Int=0
      var GPS_Latitude:String=""
      var  GPS_Longitude:String=""
                           //30s
      var  BMS_DCS_ActlChrgCurrent:String=""
      var  AC_EnvirTemp:String=""
      var  AC_IndoorTemp:String=""
      var  BMS_BatSOH:String=""
      var  TPMS_PressureValue_FL:String=""
      var  TPMS_PressureValue_FR:String=""
      var  TPMS_PressureValue_RL:String=""
      var  TPMS_PressureValue_RR:String=""
      var  VCU_VhclPwrCnsmpAvrg:String=""
              //1s
       var BMS_BatSOC_Rpt:String=""

       val eventSpeedMap: mutable.HashMap[String, String]=scala.collection.mutable.HashMap()
                        //事件    AC_ActlOprtMode  AC_FailSta  AC_HVHFailSta_HVAC
                           //AC_SeatHeatFb_FL   AC_SeatHeatFb_FR  BCM_DoorLockSta_FL  BCM_DoorLockSta_FR_RL_RR
                           // BCM_Warn_UID_LOW_BATT   BMS_DCS_ActOprtMode   BMS_DCS_InletConnectSta  BMS_SysFailSta
                           // OBC_ActlOprtMode OBC_InletConnctSta SAS_CalibSts  TMPS_AbnmPrsrWarn  TMPS_FailWarn
                           //TPMS_CtrlrSenserLearnSta  TPMS_PressureSta_FL TPMS_PressureSta_FR TPMS_PressureSta_RL
                           //TPMS_PressureSta_RR  TPMS_TempSts_FL  TPMS_TempSts_FR  TPMS_TempSts_RL  TPMS_TempSts_RR
}
