package com.gac.xs6.bigdata.core.impl


import com.alibaba.fastjson.JSON
import com.datastax.spark.connector.util.Logging
import com.gac.xs6.bigdata.core.ChargeTripAggregator
import com.gac.xs6.bigdata.model.{ChargeState, QualiferData, SourceData, TripState}
import org.apache.spark.streaming.{Duration, State, StateSpec}
import org.apache.spark.streaming.dstream.DStream

import scala.collection.mutable.ArrayBuffer


/**
  * Auther: wkf
  * Date: 2018-08-13 17:24
  * Description: 充电划分
  */
object ChargeTripAggregatorImpl extends ChargeTripAggregator with Logging{

  val chargeIdleTimeout: Duration = Duration(20 * 60 * 1000)

  val chargeDuration:Duration = Duration(15 * 60)  //15分钟   因为传过来是1970年所以不用乘以1000
  /** *
    * 充电划分计算，判断充电结束，充电结束把当前充电结果输出
    *
    * @param stream DStream(vin,SourceData)
    * @return DStream(vin,ChargeState)
    */
  override def aggregate(stream: DStream[(String, SourceData)]): DStream[(String, ChargeState)] = {
    stream
      .mapWithState(chargeStateSpec)
      .flatMap(opt => opt)
  }

  val chargeStateSpec = StateSpec.function(mappingCharge _).timeout(chargeIdleTimeout)

  def mappingCharge(vin: String, opt: Option[SourceData], state: State[ChargeState]): Option[(String, ChargeState)] = {
    if (state.isTimingOut() && state.exists()) {
      //  log.warn("超时了")
      val lastState = state.get()
      lastState.chargeStatus = "2"
      Some((vin, lastState))

    }else opt.flatMap(data=>{
        //var lastState = getLastState(data, state)
      if(null!=data){
        val chargeList=getChargeList(data.singal1sMap.get(QualiferData.BMS_ActlExchgCurnt).getOrElse(null)) //电池实际输入输出电流_负为流入，正为流出
        val speedList=getSpeedList(data.singal1sMap.get(QualiferData.ESC_VehicleSpeed).getOrElse(null))//车辆速度

        var resultStatus:Option[(String, ChargeState)]=None

        if (state.exists()) {
          var lastState=state.get()
          val newTime = data.createTime.getTime
          if (newTime - lastState.carTime < 0) {
            // 过滤 当前时间早于lastcartime的数据
            resultStatus=None
          } else {
            //缓存中有数据
            if (speedList != null && speedList.size > 0 && chargeList != null && chargeList.size > 0) {
              //有速度数组说明速度是0
              //说明有充电   先不判断是快充慢充
              //缓存有数据 又在充电中  更新数据

              lastState = getUpdateState(data, lastState, chargeList(0))
              state.update(lastState)
              resultStatus = None
            } else {
              //缓存中有数据  速度或者充电不为负 结束充电
              //充电结束   赋结束的值
              lastState = getFinalState(data, lastState)
              state.remove()
              resultStatus = Some((vin, lastState))
            }
          }
        } else {
           //缓存中没有数据
          if(speedList!=null && speedList.size>0 && chargeList!=null && chargeList.size>0){
            //有速度数组说明速度是0
            //说明有充电   先不判断是快充慢充
            //速度和充电条件同时满足，初始化
            initState(data, state)   //isFastCharge //0是慢速充电 1是快充
            resultStatus=None
          }
        }

        resultStatus
      }else{
        None
      }
    })
  }

  def getChargeList(str:String):ArrayBuffer[Int]= {
    val arrayMode = JSON.parseArray(str)
      if (arrayMode != null && arrayMode.size() > 0) {
        var slowList=scala.collection.mutable.ArrayBuffer[Int]()
        var index=0

        var isFlag=true
        for(i <- 0 until arrayMode.size() if isFlag){
          val value=arrayMode.getInteger(i)/10-1000
          if(value<0){   //电流为负 说明充电
            slowList+=value
            index=0   //每次电流为负 就重置index 0
          }else{
            index+=1  //说明是为了过滤连续十五个电流为正，就清空数组  否则不连续超过十五个电流为负的算干扰数据，会继续计算充电
            if(index==15){
              slowList.clear()
              isFlag=false
              index=0
            }
          }
        }
        slowList
      }else{
        null
      }
  }

  def getSpeedList(str:String):ArrayBuffer[Int]= {
    val arrayMode = JSON.parseArray(str)
    if (arrayMode != null && arrayMode.size() > 0) {
      var speedList=scala.collection.mutable.ArrayBuffer[Int]()
      var index=0
      var isFlag=true
      for(i <- 0 until arrayMode.size() if isFlag){
        val value=arrayMode.getInteger(i)
        if((value<2 && value>(-2)) || value>180){   //瞬时速度小于2
          speedList+=value
          index=0   //每次速度小于2 大于-2 或者速度大于180 就重置index 0
        }else{
          index+=1  //说明是为了过滤连续十个速度都是大于2的速度，就清空数组  否则不连续超过十个速度的算干扰数据，会继续计算充电
          if(index==10){
            speedList.clear()
            isFlag=false
            index=0
          }
        }
      }
      speedList
    }else{
      null
    }
  }
      //充电结束状态
   def getFinalState(data: SourceData, lastState: ChargeState): ChargeState ={
        lastState.endTime=data.createTime.getTime

        //充电结束   赋结束的值
        lastState.carTime=data.createTime.getTime
        lastState.chargeStatus = "1"
        //lastState.coordinate.append(connectobd(data.GPS_Latitude,data.GPS_Longitude))
        if(!"0.0".equals(data.GPS_Latitude) && !"0.0".equals(data.GPS_Longitude)){
           lastState.coordinate.append(connectobd(data.GPS_Latitude,data.GPS_Longitude))
        }

        val arraySoc=JSON.parseArray(data.singal1sMap.get(QualiferData.BMS_BatSOC_Rpt).getOrElse(null))
        var endSoc:Double=0
        //去soc数组的第0条赋值结束的电量
        if(arraySoc!=null && arraySoc.size()>0){
          endSoc=arraySoc.getInteger(0)/10.0
        }
        lastState.endSoc=endSoc

        if(endSoc.toInt>=100)    //充满电 记录循环充电一次
           lastState.loopChargeNum+=1

        lastState
  }

  def getUpdateState(data: SourceData, lastState: ChargeState,value:Int): ChargeState = {
      lastState.carTime=data.createTime.getTime
      lastState.endTime=data.createTime.getTime
      if(value<(-20)){
        lastState.indexFast+=1
        //是为了记录连续性 连续超过4个批次的数据都小于20A 说明是快速充电    每次有大于-20A的重置indexFast为0
      }else{
        lastState.indexFast=0
      }
      if(lastState.indexFast==4){
        //如果连续记录小于-20A 4次也就是2分钟  说明快充
        lastState.isFastCharge=1
      }

      lastState.chargeStatus="0"

    val arraySoc=JSON.parseArray(data.singal1sMap.get(QualiferData.BMS_BatSOC_Rpt).getOrElse(null))
    var endSoc:Double=0
    //去soc数组的第0条赋值结束的电量
    if(arraySoc!=null && arraySoc.size()>0){
      endSoc=arraySoc.getInteger(0)/10.0
    }
    lastState.endSoc=endSoc

    if(endSoc.toInt>=100)    //充满电 记录循环充电一次
      lastState.loopChargeNum+=1

    lastState.temperature=String.valueOf(Integer.parseInt(data.singal30sMap.get(QualiferData.AC_EnvirTemp).getOrElse("0"))*0.5-40)


    lastState
  }

  def connectobd(x:String,y:String):String={
    val result=(x+":"+y+",")
    result
  }

  /***
    * 初始化state
    */
  def initState(sourceData: SourceData,state: State[ChargeState])={
    //经纬度
    val coordinate:StringBuffer=new StringBuffer()
    if(!"0.0".equals(sourceData.GPS_Latitude) && !"0.0".equals(sourceData.GPS_Longitude)){
       coordinate.append(connectobd(sourceData.GPS_Latitude,sourceData.GPS_Longitude))
    }

    val arraySoc=JSON.parseArray(sourceData.singal1sMap.get(QualiferData.BMS_BatSOC_Rpt).getOrElse(null))
    var startSoc:Double=0
    //去soc数组的第0条赋值初始电量
    if(arraySoc!=null && arraySoc.size()>0){
      startSoc=arraySoc.getInteger(0)/10.0
    }

    val startTime:Long=sourceData.createTime.getTime
    val endTime:Long=sourceData.createTime.getTime

    val initTripState=ChargeState(
      id = sourceData.vin,
      carTime=sourceData.createTime.getTime,
      startTime,
      endTime,
      loopChargeNum = 0,
      coordinate,
      isFastCharge=0,  //初始化的时候 标记为慢充
      startSoc,
      endSoc=0,
      temperature="0"
    )
    state.update(initTripState)
  }
}
