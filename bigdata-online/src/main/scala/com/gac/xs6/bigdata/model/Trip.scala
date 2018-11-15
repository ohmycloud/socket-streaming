package com.gac.xs6.bigdata.model


//行程结果
case class TripState
(
  var id: String,
  var tripStartTime: Long,
  var tripEndTime: Long,
  var startMileage:Double,
  var tripDistance: Double,//行程内行驶公里数
  var tripStatus: String = "0",
  var coordinate:StringBuffer=new StringBuffer(),
  var maxSpeed:Int=0,
  var startSoc:Double=0,
  var endSoc:Double=0,
  var totalNum:Int=0,    //样例筛选经纬度的
  var temperature:String=""
)

//充电结果
case class ChargeState
(
  var id: String,
  var carTime:Long,//汽车传入时间
  var startTime: Long=0,   //充电起始时间
  var endTime: Long=0,     //充电结束时间
  var loopChargeNum: Int=0,    //循环充电次数
  var coordinate:StringBuffer=new StringBuffer(),
  var isFastCharge:Int=0, //0是慢速充电 1是快充
  var startSoc:Double=0,
  var endSoc:Double=0,
  var chargeStatus:String="0",
  var temperature:String,
  var indexFast:Int=0
)

//里程异常结果
case class EventState
(
  var id: String,
  var carTime:Long,//汽车传入时间
  var mileage:Int,  //里程异常值
  var last_mileage:Int,  //上一条的里程值
  var GPS_Latitude:String,   //纬度
  var GPS_Longitude:String  //经度
)


//行程结果
case class WaringData
(
  var vin: String,
  var carTime:Long,//汽车传入时间
  var coordinate:String
)

