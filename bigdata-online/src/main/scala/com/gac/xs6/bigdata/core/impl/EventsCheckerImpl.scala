package com.gac.xs6.bigdata.core.impl

import com.datastax.spark.connector.util.Logging
import com.gac.xs6.bigdata.core.EventsChecker
import com.gac.xs6.bigdata.model.{Event, SourceData}
import org.apache.spark.streaming.dstream.DStream

/**
  *
  */
object EventsCheckerImpl extends EventsChecker with Logging {

  /**
    *
    * @param stream
    * @return
    */
  override def check(stream: DStream[(String, SourceData)]): DStream[(String, Event)] = ???
}
