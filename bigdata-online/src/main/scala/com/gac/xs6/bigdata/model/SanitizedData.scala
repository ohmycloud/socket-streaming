package com.gac.xs6.bigdata.model

import org.joda.time.Instant

/**
  * 数据清洗
  */
case class SanitizedData
(
  vin: String,
  carTime: Instant,  //数据产生时间
  createTime: Instant,
  coordinate: Coordinate,
  mileage: BigDecimal, //total mileage since trip begins
  speed: BigDecimal
)

/***
  * 坐标
  */
case class Coordinate(longitude: Double, latitude: Double) {
  val M_PER_DEG_LONG: Double = 102834.74258026089786013677476285
  // meter per degree of longitude
  val M_PER_DEG_LAT: Double = 111712.69150641055729984301412873 // meter per degree of latitude

  def distanceInMeter(other: Coordinate) = {
    val b = Math.abs((other.longitude - this.longitude) * M_PER_DEG_LONG)
    val a = Math.abs((other.latitude - this.latitude) * M_PER_DEG_LAT)
    Math.sqrt(a * a + b * b)
  }
}

case class SanitizedState
(
  lastCarTime: Long,
  lastValidCoordinate: Coordinate,
  lastValidCoordinateTime: Long,
  mileage: BigDecimal,
  lastSpeed: BigDecimal
)

