package com.gac.xs6.bigdata.core.impl

import com.datastax.spark.connector.util.Logging
import com.gac.xs6.bigdata.model._
import org.apache.spark.streaming.{Duration, State, StateSpec}
import org.apache.spark.streaming.dstream.DStream
import com.gac.xs6.bigdata.core.SubTrip


object SubTripImpl extends SubTrip with Logging{

  val tripIdleTimeout: Duration = Duration(30 * 1000)  // mapWithState 的超时时间, 假设为 30 秒
  val tripDuration = Duration(10 * 1000) // 下一条数据超过 10 秒种才传过来


  override def aggregate(stream: DStream[(String,SourceData)]): DStream[(String,TripState)] = {
    stream
      .mapWithState(tripStateSpec)
      .flatMap(opt => opt)
  }

  val tripStateSpec = StateSpec.function(mappingTrip _).timeout(tripIdleTimeout)

  def mappingTrip(vin: String, opt: Option[SourceData], state: State[TripState]): Option[(String,TripState)] = {
    if (state.isTimingOut() && state.exists()) {
      val lastState = state.get()          // 获取旧的 state
      lastState.tripStatus = "2"           // 将这种行程的状态更新为 2
      if(lastState.tripDistance >= 0.5) {  // 里程大于 0.5 米的为有效行程
        Some((vin,lastState))              // 返回更新后的 TripState, send downstream， 发送到下游
      } else {
        None
      }
    } else opt.flatMap(data => {

      val newTime   = data.createTime
      var lastState = getLastState(data, state)

      if (newTime - lastState.tripEndTime < 0) {
        None
      } else {
        if ((newTime - lastState.tripEndTime) * 1000L > tripDuration.milliseconds) {
          println("行程划分超时   " + (newTime - lastState.tripEndTime) * 1000L + "   " + tripDuration.milliseconds)

          lastState.tripStatus   = "1"
          val endMileage: Long   = data.mileage
          lastState.tripDistance = endMileage - lastState.startMileage

          initState(data, state)

          if(lastState.tripDistance >= 0.5) { // 但返回划分行程后的 tripState
            Some((vin,lastState))
          } else {
            None
          }
        } else {
          lastState = getUpdateState(data, lastState)
          state.update(lastState)
          if (lastState.tripStatus.equals("1")) {
            println("[debug] update trip: " + vin + " last car time " + lastState.tripEndTime + " trip start time " + lastState.tripStartTime)
          }
          None
        }
      }
    })
  }

  def getUpdateState(data: SourceData, lastState: TripState): TripState = {
    lastState.tripEndTime  = data.createTime
    lastState.tripStatus   = "0"
    val endMileage: Long   = data.mileage
    lastState.tripDistance = endMileage - lastState.startMileage
    lastState
  }


  def getLastState(sourceData: SourceData, state: State[TripState]): TripState = {
    if (state.exists()) {
      state.get()
    } else {
      var startMileage: Long = 0
      startMileage = sourceData.mileage

      TripState(
        vin           = sourceData.vin,
        tripStartTime = sourceData.createTime,
        tripEndTime   = sourceData.createTime,
        startMileage,
        tripDistance  = 0,
        tripStatus    = "0"
      )
    }
  }


  def initState(sourceData: SourceData,state: State[TripState])= {

    var startMileage: Long = 0
    startMileage = sourceData.mileage

    val initTripState = TripState(
      vin           = sourceData.vin,
      tripStartTime = sourceData.createTime,
      tripEndTime   = sourceData.createTime,
      startMileage,
      tripDistance  = 0,
      tripStatus = "0"
    )
    state.update(initTripState)
  }
}
