package com.gac.xs6.bigdata.util

import java.io.ByteArrayInputStream
import java.util.zip.GZIPInputStream
import java.util.{Calendar, Date}

import org.apache.commons.io.IOUtils

/**
  * Created by azhe on 18-4-3.
  */
object DateUtil extends Serializable{

  def getScheduleTime: Date = {
    val c: Calendar = Calendar.getInstance()
    //    c.set(Calendar.DAY_OF_WEEK, 7)//每周六晚上0:00
    //    c.set(Calendar.HOUR_OF_DAY, 0)
    c.set(Calendar.MINUTE, 0)//每隔一个小时更新一次
    c.set(Calendar.SECOND, 0)
    c.getTime
  }

  //解压gzip包
  def decompress(array: Array[Byte]): String = {
    val bais = new ByteArrayInputStream(array)
    val gis: GZIPInputStream = new GZIPInputStream(bais)
    val result = new String(IOUtils.toByteArray(gis), "utf-8")
    IOUtils.closeQuietly(bais)
    IOUtils.closeQuietly(gis)
    result
  }
}
