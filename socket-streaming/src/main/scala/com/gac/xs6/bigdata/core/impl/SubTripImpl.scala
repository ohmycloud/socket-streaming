package com.gac.xs6.bigdata.core.impl

import com.datastax.spark.connector.util.Logging
import com.gac.xs6.bigdata.model._
import org.apache.spark.streaming.{Duration, State, StateSpec}
import org.apache.spark.streaming.dstream.DStream
import com.gac.xs6.bigdata.core.SubTrip

/**
  * 行程划分
  * 1、首先启动 perl6 write2socket.pl6
  * 2、再运行 BigdataApplication
  */
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
    } else opt.flatMap( data => {

      val newTime   = data.createTime
      var lastState = getLastState(data, state) // 返回一个 lastState 对象

      if (newTime - lastState.tripEndTime < 0) {
        None
      } else {
        if ((newTime - lastState.tripEndTime) * 1000L > tripDuration.milliseconds) { // 正常划分行程
          println("行程划分超时   " + (newTime - lastState.tripEndTime) * 1000L + "   " + tripDuration.milliseconds)

          lastState.tripStatus   = "1"          // 这种类型的行程是正常结束的行程, 将行程状态置为 1
          val endMileage: Long   = data.mileage // 把新的源数据的开始里程作为上一个行程的结束里程
          lastState.tripDistance = endMileage - lastState.startMileage // 计算行驶距离

          initState(data, state)  // 在内存中初始化下一个行程(这里更新了内存中的 state)

          if(lastState.tripDistance >= 0.5) { // 但返回划分行程后的 tripState
            Some((vin,lastState))
          } else {
            None
          }
        } else { // 行程正在进行
          lastState = getUpdateState(data, lastState)
          state.update(lastState)
          None // 在内存中更新行程, 不返回东西
        }
      }
    })
  }

  /**
    * 行程正在进行, 则更新内存中的 state 对象，不划分
    * @param data 新的源数据
    * @param lastState 上一个行程的状态
    * @return
    */
  def getUpdateState(data: SourceData, lastState: TripState): TripState = {
    lastState.tripEndTime  = data.createTime // 行程正在进行, 将上一个行程的行程结束时间置为新的源数据的 createTime
    lastState.tripStatus   = "0"             // 行程正在进行, 将这种类型的行程的状态置为 0
    val endMileage: Long   = data.mileage
    lastState.tripDistance = endMileage - lastState.startMileage // 行程正在进行, 更新当前行程的行驶里程数
    lastState
  }

  /**
    * 获取内存中的旧的状态
    * @param sourceData 新的源数据
    * @param state 内存中的旧状态
    * @return 返回刷新后的行程状态
    */
  def getLastState(sourceData: SourceData, state: State[TripState]): TripState = {
    if (state.exists()) {
      state.get() // 如果存在旧的状态, 则直接获取内存中的状态
    } else {      // 如果状态不存在, 则 sourceData 是新的源数据

      TripState(
        vin           = sourceData.vin,
        tripStartTime = sourceData.createTime, // 将新的源数据中的 createTime 作为行程开始时间
        tripEndTime   = sourceData.createTime, // 将新的源数据中的 createTime 作为行程结束时间
        startMileage  = sourceData.mileage,    // 将新的源数据中的 mileage 作为行程的当前里程数
        tripDistance  = 0,  // 行程刚开始, 将行程的行驶距离置为 0
        tripStatus    = "0" // 这种类型的行程是刚开始的行程, 将行程状态设置为 0
      )
    }
  }

  /**
    * 初始化下一个行程
    * @param sourceData 新的源数据
    * @param state 内存中的旧状态
    */
  def initState(sourceData: SourceData,state: State[TripState])= {

    val initTripState = TripState(
      vin           = sourceData.vin,
      tripStartTime = sourceData.createTime, // 用新的源数据中的 createTime 初始化行程开始时间
      tripEndTime   = sourceData.createTime, // 用新的源数据中的 createTime 初始化行程结束时间
      startMileage  = sourceData.mileage,    // 用新的源数据中的 mileage 初始化行程中的行程开始里程
      tripDistance  = 0,  // 初始行驶距离置为 0
      tripStatus    = "0" // 初始行程状态置为 0
    )
    state.update(initTripState) // 用新的 tripState 对象更新内存中旧的 state
  }
}
