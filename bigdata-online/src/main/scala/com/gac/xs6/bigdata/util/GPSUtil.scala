package com.gac.xs6.bigdata.util

/**
  * Created by azhe on 18-4-3.
  */
object GPSUtil {
  val pi = 3.1415926535897932384626
  val a = 6378245.0
  val ee = 0.00669342162296594323
  val x_PI = pi * 3000.0 / 180.0

  def main(args: Array[String]): Unit = {
    println(gps84_To_Gcj02(116.39134, 39.905461))
    val gcj: (String, String) = gps84_To_Gcj02(116.39134, 39.905461)
    println(gcj._1+ gcj._2)
    //    print(gps84_To_Gcj02(113.050788333333,28.2146866666666))
  }

  /**
    * WGS84（地球坐标）转GCJ02（火星坐标）
    **/
  def gps84_To_Gcj02(longitude: Double, latitude: Double): (String, String) = {
    if (longitude < 72.004 || longitude > 137.8347 || latitude < 0.8293 || latitude > 55.8271) {
      ("0", "0")
    } else {
      var dLat = transformLat(longitude - 105.0, latitude - 35.0)
      var dLon = transformLon(longitude - 105.0, latitude - 35.0)
      val radLat = latitude / 180.0 * pi
      var magic = Math.sin(radLat)
      magic = 1 - ee * magic * magic
      val sqrtMagic = Math.sqrt(magic)
      dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi)
      dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi)
      val mgLat = latitude + dLat
      val mgLon = longitude + dLon
      (mgLon.toString,mgLat.toString)
    }
  }

  /**
    * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换
    * 即谷歌、高德 转 百度
    **/
  def gcj02tobd09(lng: Double, lat: Double): (Double, Double) = {
    val z: Double = Math.sqrt(lng * lng + lat * lat) + 0.00002 * Math.sin(lat * x_PI)
    val theta: Double = Math.atan2(lat, lng) + 0.000003 * Math.cos(lng * x_PI)
    val bd_lng: Double = z * Math.cos(theta) + 0.0065
    val bd_lat: Double = z * Math.sin(theta) + 0.006
    (bd_lng, bd_lat)
  }


  def transformLat(x: Double, y: Double): Double = {
    var ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x))
    ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0
    ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0
    ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0
    ret
  }



  def transformLon(x: Double, y: Double): Double = {
    var ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x))
    ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0
    ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0
    ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0
    ret
  }
}