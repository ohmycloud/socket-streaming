package com.gac.xs6.bigdata.dao

import java.sql.{Connection, Date, Timestamp}
import java.text.SimpleDateFormat
import com.gac.xs6.bigdata.model._
import scala.collection.mutable.ListBuffer
/**
  * Auther: wkf
  * Date: 2018-08-13 17:24
  * Description: mysqlDao
  */
class MysqlSourceDao {

}
object MysqlSourceDao {

  lazy val  df=new  java.text.DecimalFormat("#0.0")

  lazy val  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  // 充电划分结果写入 mysql数据库    批量插入
  def saveCharge(con: Connection, list: List[ChargeState]): Unit = {
    if(null!=list && list.length>0) {
      try {
        val sql = "insert into charge_signal(vin,slowChargeStartTime,slowChargeEndTime,loopChargeNum,isFastCharge,chargeStatus,coordinate,startSoc,endSoc,carTime,temperature) values(?,?,?,?,?,?,?,?,?,?,?);"
        con.setAutoCommit(false)
        val ps = con.prepareStatement(sql)
        for (i <- 0 until list.length) {
          val data: ChargeState = list(i)
          if (null != data && !data.coordinate.toString.contains("0:0")) {
            //过滤掉充电小于2分钟数据
            if (data.endTime-data.startTime>120) {
              ps.setString(1, data.id)
              ps.setLong(2, data.startTime)
              ps.setLong(3, data.endTime)
              ps.setInt(4, data.loopChargeNum)
              ps.setInt(5, data.isFastCharge)
              ps.setString(6, data.chargeStatus)
              var value = data.coordinate.toString
              if (value.endsWith(",")) {
                value = value.substring(0, value.length() - 1)
              }
              ps.setString(7, value)
              ps.setFloat(8, df.format(data.startSoc).toFloat)
              ps.setFloat(9, df.format(data.endSoc).toFloat)
              ps.setLong(10, data.carTime)
              ps.setString(11, data.temperature)
              ps.addBatch()
            }
          }
        }
        // 执行批量更新
        ps.executeBatch()
        // 语句执行完毕，提交本事务
        con.commit()
        ps.close()
      } catch {
        case exception: Exception =>
          println("Error in execution of query " + exception.getMessage + "\n-----------------------\n" + exception.printStackTrace() + "\n-----------------------------")
      }
    }
  }



  //行程划分结果写入 mysql数据库    批量插入
  def saveTrip(con: Connection, list: List[TripState]): Unit = {
    if(null!=list && list.length>0) {
      try {
        val sql = "insert into trip_signal(vin,tripStartTime,tripEndTime,tripStatus,startSoc,endSoc,maxSpeed,startMileage,tripDistance,startTime,endTime,coordinate) values(?,?,?,?,?,?,?,?,?,?,?,?);"
        con.setAutoCommit(false)
        val ps = con.prepareStatement(sql)
        for (i <- 0 until list.length) {
          val data: TripState = list(i)
          if (null != data) {
              ps.setString(1, data.id)
              ps.setLong(2, data.tripStartTime)
              ps.setLong(3, data.tripEndTime)
              ps.setInt(4, data.tripStatus.toInt)
              ps.setFloat(5, df.format(data.startSoc).toFloat)
              ps.setFloat(6, df.format(data.endSoc).toFloat)
              ps.setInt(7, data.maxSpeed)
              ps.setFloat(8, df.format(data.startMileage).toFloat)
              ps.setFloat(9, df.format(data.tripDistance).toFloat)
              ps.setString(10,sdf.format(new Date(data.tripStartTime*1000L)))
              ps.setString(11,sdf.format(new Date(data.tripEndTime*1000L)))
              var value = data.coordinate.toString
              if (value.endsWith(",")) {
                value = value.substring(0, value.length() - 1)
              }
              ps.setString(12, value)
              ps.addBatch()
            }

        }
        // 执行批量更新
        ps.executeBatch()
        // 语句执行完毕，提交本事务
        con.commit()
        ps.close()
      } catch {
        case exception: Exception =>
          println("Error in execution of query " + exception.getMessage + "\n-----------------------\n" + exception.printStackTrace() + "\n-----------------------------")
      }
    }
  }


  // 里程异常 因为要手动调表才有里程异常 所以不需要批量写入
  def saveMaile(con: Connection, data: EventState): Unit = {
    // println(data.toString)
      try {
        val ps = con.prepareStatement("insert into mile_signal(vin,carTime,mileage,next_mileage,latitude,longitude) values(?,?,?,?,?,?);")
        ps.setString(1, data.id)
        ps.setLong(2, data.carTime)
        ps.setInt(3, data.mileage)
        ps.setInt(4, data.last_mileage)
        ps.setString(5, data.GPS_Latitude)
        ps.setString(6, data.GPS_Longitude)
        ps.executeUpdate()
        ps.close()

      } catch {
        case exception: Exception =>
          println("Error in execution of query " + exception.getMessage + "\n-----------------------\n" + exception.printStackTrace() + "\n-----------------------------")
      }
  }


  // 防盗警报信息 批量更新
  def saveBodyWaring(conn: Connection,list:ListBuffer[WaringData]): Unit = {
    if(null!=list && list.length>0){
     // val sb=new StringBuffer()
      //insert into test_tbl (id,dr) values (1,'2'),(2,'3'),...(x,'y') on duplicate key update dr=values(dr);
      val sql="INSERT INTO warning_data(vin,carTime,coordinate) VALUES (?,?,?)"
      conn.setAutoCommit(false)
      val ps = conn.prepareStatement(sql)
      try {
        for (i <- 0 until list.length) {
          val o:WaringData=list(i)
          if(null!=o){
            ps.setString(1,o.vin)
            ps.setLong(2,o.carTime)
            ps.setString(3,o.coordinate)
            ps.addBatch()
          }
        }
      //  val sql=sb.substring(0,sb.length()-1).toString+";";
       // println(sql)
       // val ps = conn.prepareStatement(sql)
        // 执行批量更新
        ps.executeBatch()
        // 语句执行完毕，提交本事务
        conn.commit()
        ps.close()
      } catch {
        case exception: Exception =>
          println("Error in execution of query " + exception.getMessage + "\n-----------------------\n" + exception.printStackTrace() + "\n-----------------------------")
      }
    }
  }
}