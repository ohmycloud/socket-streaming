package com.gac.xs6.bigdata.core.impl


import com.datastax.spark.connector.util.Logging
import com.gac.xs6.bigdata.core.DrivingTripAggregator
import com.gac.xs6.bigdata.model._
import org.apache.spark.streaming.{Duration, State, StateSpec}
import org.apache.spark.streaming.dstream.DStream

import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}
/**
  * Auther: wkf
  * Date: 2018-08-13 17:24
  * Description: 行程划分
  */
object DrivingTripAggregatorImpl extends DrivingTripAggregator with Logging{

  val tripIdleTimeout: Duration = Duration(15 * 60 * 1000)

  val tripDuration = Duration(5 * 60)  //下一条超时时间超过5分钟   因为传入的是1970年所以不用乘以1000

  /** *
    * 行程统计计算，判断行程结束，行程结束把当前行程结果输出
    *
    * @param stream DStream(vin,EnRichedRegion)
    * @return DStream(vin,Trip)
    */
  override def aggregate(stream: DStream[(String, SourceData)]): DStream[(String, TripState)] = {
    stream
      .mapWithState(tripStateSpec)
      .flatMap(opt => opt)
  }
  val tripStateSpec = StateSpec.function(mappingTrip _).timeout(tripIdleTimeout)

  /**
    * 该方法用于过滤不符合字段阈值的数据
    * @param obd
    * @return
    */
  def isValidData(obd: SourceData): Boolean = {
    try {
        obd match {
          // case data if data.vin.length != 17 => false // 过滤vin.length!=17的数据
          case data if data.GPS_Latitude == null || "".equals(data.GPS_Latitude) || data.GPS_Latitude.toDouble.toInt <= 0 => false // 过滤GPS_Latitude的不合法数据
          case data if data.GPS_Longitude == null || "".equals(data.GPS_Longitude) || data.GPS_Longitude.toDouble.toInt <= 0 => false // 过滤GPS_Longitude的不合法数据
          //case data if data.createTime.getTime <= 0 => false // 过滤carTime<=0的数据
          case _ => true
        }
    }catch {
      case e: Exception => println(e)
        true
    }
  }
  def mappingTrip(vin: String, opt: Option[SourceData], state: State[TripState]): Option[(String, TripState)] = {
    if (state.isTimingOut() && state.exists()) {
      //  log.warn("超时了")
      val lastState = state.get()
      lastState.tripStatus = "2"
      if(lastState.tripDistance>=0.5){
        Some((vin, lastState))
      }else{
        None
      }
    } else opt.filter(isValidData).flatMap(data => {
      val newTime = data.createTime.getTime
      var lastState = getLastState(data, state)
      if (newTime - lastState.tripEndTime < 0) {
        // 过滤 当前时间早于lastcartime的数据
        None
      } else {
        if ((newTime-lastState.tripEndTime)>tripDuration.milliseconds) {
         // println("行程划分超时   "+(newTime-lastState.tripEndTime)+"   "+tripDuration.milliseconds)
          //行程结束   赋结束的值
          lastState.tripStatus = "1"
          lastState.coordinate.append(connectobd(data.GPS_Latitude,data.GPS_Longitude,data.singal30sMap.get(QualiferData.GPS_Speed).getOrElse(null),data.singal30sMap.get(QualiferData.GPS_Heading).getOrElse(null),data.createTime.getTime))
          val arraySoc=JSON.parseArray(data.singal1sMap.get(QualiferData.BMS_BatSOC_Rpt).getOrElse(null))
          var endSoc:Double=0
          //去soc数组的第0条赋值结束的电量
          if(arraySoc!=null && arraySoc.size()>0){
              endSoc=arraySoc.getInteger(0)/10.0
          }
          lastState.endSoc=endSoc
         val endMileage:Double=data.last_meter/10.0
          //更新里程数
          lastState.tripDistance=BigDecimal.apply(endMileage).-(BigDecimal.apply(lastState.startMileage)).doubleValue()

          //创建新的state  并赋初始值
          initState(data, state)

          if(lastState.tripDistance>=0.5){
            Some((vin, lastState))
          }else{
            None
          }
        } else {
          //行程进行中  赋累计计算 结束的  需要更新的
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
    lastState.tripEndTime=data.createTime.getTime
    lastState.tripStatus="0"
    lastState.totalNum+=1
    if(lastState.totalNum==4){
        lastState.coordinate.append(connectobd(data.GPS_Latitude,data.GPS_Longitude,data.singal30sMap.get(QualiferData.GPS_Speed).getOrElse(null),data.singal30sMap.get(QualiferData.GPS_Heading).getOrElse(null),data.createTime.getTime))
        lastState.totalNum=0
    }

    var maxSpeed=getMaxSpeed(data.singal1sMap.get(QualiferData.ICU_DisplayVehicleSpeed).getOrElse("[0]"))

    if(lastState.maxSpeed<maxSpeed){
      //保存最大速度
      lastState.maxSpeed=maxSpeed
    }

    val arraySoc=JSON.parseArray(data.singal1sMap.get(QualiferData.BMS_BatSOC_Rpt).getOrElse(null))
    var endSoc:Double=0
    //去soc数组的第0条赋值结束的电量
    if(arraySoc!=null && arraySoc.size()>0){
      endSoc=arraySoc.getInteger(0)/10.0
    }
    lastState.endSoc=endSoc

    /*val arrayMileage=JSON.parseArray(data.singal1sMap.get(QualiferData.ICU_ICUTotalOdometer).getOrElse(null))
    var endMileage=0
    //初始化其实行程的公里数
    if(arrayMileage!=null && arrayMileage.size()>0){
      endMileage=arrayMileage.getInteger(0)
    }*/
    val endMileage:Double=data.last_meter/10.0
    //更新里程数
    lastState.tripDistance=BigDecimal.apply(endMileage).-(BigDecimal.apply(lastState.startMileage)).doubleValue()

    lastState.temperature=String.valueOf(Integer.parseInt(data.singal30sMap.get(QualiferData.AC_EnvirTemp).getOrElse("0"))*0.5-40)

    lastState
  }
  def getLastState(sourceData: SourceData, state: State[TripState]): TripState = {
    if (state.exists()) {
      state.get()
    } else {
      val coordinate:StringBuffer=new StringBuffer()
      coordinate.append(connectobd(sourceData.GPS_Latitude,sourceData.GPS_Longitude,sourceData.singal30sMap.get(QualiferData.GPS_Speed).getOrElse(null),sourceData.singal30sMap.get(QualiferData.GPS_Heading).getOrElse(null),sourceData.createTime.getTime))
      val arraySoc=JSON.parseArray(sourceData.singal1sMap.get(QualiferData.BMS_BatSOC_Rpt).getOrElse(null))
      var startSoc:Double=0
      //去soc数组的第0条赋值初始电量
      if(arraySoc!=null && arraySoc.size()>0){
        startSoc=arraySoc.getInteger(0)/10.0
      }

     val startMileage:Double=sourceData.last_meter/10.0

      var maxSpeed=getMaxSpeed(sourceData.singal1sMap.get(QualiferData.ICU_DisplayVehicleSpeed).getOrElse("[0]"))

      var endSoc:Double=0
      //去soc数组的第0条赋值结束的电量
      if(arraySoc!=null && arraySoc.size()>0){
        endSoc=arraySoc.getInteger(0)/10.0
      }


      TripState(
        id = sourceData.vin,
        tripStartTime =sourceData.createTime.getTime,
        tripEndTime =sourceData.createTime.getTime,
        startMileage,
        tripDistance = 0,
        tripStatus = "0",
        coordinate,
        maxSpeed ,
        startSoc,
        endSoc,
        temperature=String.valueOf(Integer.parseInt(sourceData.singal30sMap.get(QualiferData.AC_EnvirTemp).getOrElse("0"))*0.5-40)
      )

    }
  }
  def connectobd(x:String,y:String,sp:String,ag:String,tm:Long):String={
    val result=(x+":"+y+":"+sp+":"+ag+":"+tm+",")
    result
  }
  /***
    * 初始化state
    */
  def initState(sourceData: SourceData,state: State[TripState])={
    val coordinate:StringBuffer=new StringBuffer()
    coordinate.append(connectobd(sourceData.GPS_Latitude, sourceData.GPS_Longitude,sourceData.singal30sMap.get(QualiferData.GPS_Speed).getOrElse(null),sourceData.singal30sMap.get(QualiferData.GPS_Heading).getOrElse(null),sourceData.createTime.getTime))
    val arraySoc=JSON.parseArray(sourceData.singal1sMap.get(QualiferData.BMS_BatSOC_Rpt).getOrElse(null))
    var startSoc:Double=0
    //去soc数组的第0条赋值初始电量
    if(arraySoc!=null && arraySoc.size()>0){
      startSoc=arraySoc.getInteger(0)/10.0
    }

  val startMileage:Double=sourceData.last_meter/10.0

    val maxSpeed=getMaxSpeed(sourceData.singal1sMap.get(QualiferData.ICU_DisplayVehicleSpeed).getOrElse("[0]"))

    val initTripState=TripState(
      id = sourceData.vin,
      tripStartTime =sourceData.createTime.getTime,
      tripEndTime =sourceData.createTime.getTime,
      startMileage ,
      tripDistance = 0,
      tripStatus = "0",
      coordinate,
      maxSpeed,
      startSoc,
      endSoc = 0,
      temperature="0"
    )
    state.update(initTripState)
  }

  def getMaxSpeed(str:String):Int={
    val arrayMode = JSON.parseArray(str)
    var maxSeed=0
    if (null != arrayMode  && arrayMode.size()>0) {
      for(i <- 0 until arrayMode.size()){
          val tmp=arrayMode.getInteger(i)
           if(maxSeed<tmp){
             maxSeed=tmp
           }
      }
    }
    maxSeed
  }
}
