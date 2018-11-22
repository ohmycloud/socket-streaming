package com.gac.xs6.bigdata.core.impl

import com.alibaba.fastjson.JSON
import com.datastax.spark.connector.util.Logging
import com.gac.xs6.bigdata.core.Adapter
import com.gac.xs6.bigdata.model.SourceData
import org.apache.spark.streaming.dstream.DStream


object AdapterImpl extends Adapter with Logging {

  override def extract(sourceDstream: DStream[String]): DStream[(String,SourceData)] = {
    sourceDstream.map { r =>
      val data = JSON.parseObject(r)

      val vin = data.getString("vin")
      val createTime = data.getLong("createTime")
      val mileage = data.getLong("mileage")
      (vin,SourceData(vin, createTime, mileage))
    }
  }
}
