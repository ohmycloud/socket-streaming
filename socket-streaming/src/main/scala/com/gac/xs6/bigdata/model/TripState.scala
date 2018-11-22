package com.gac.xs6.bigdata.model

/**
  * 行程划分结果
  * @param vin           车架号
  * @param tripStartTime 行程开始时间
  * @param tripEndTime   行程结束时间
  * @param startMileage  开始里程
  * @param tripDistance  行驶距离
  * @param tripStatus    行程状态
  */
case class TripState (
  vin: String,
  var tripStartTime: Long,
  var tripEndTime:   Long,
  var startMileage:  Long,
  var tripDistance:  Long,
  var tripStatus: String = "0"
)
