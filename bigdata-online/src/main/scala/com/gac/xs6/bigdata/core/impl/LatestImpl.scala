package com.gac.xs6.bigdata.core.impl

import com.datastax.spark.connector.util.Logging
import com.gac.xs6.bigdata.core.Latest
import com.gac.xs6.bigdata.model.SourceData
import org.apache.spark.streaming.dstream.DStream

object LatestImpl extends Latest with Logging {

  /**
    * 存最新的一条数据
    * @param stream
    * @return
    */
  override def getLatest(stream: DStream[(String, SourceData)]): DStream[(String, SourceData)] = ???
}
