package com.gac.xs6.bigdata.core

import com.gac.xs6.bigdata.model.{SourceData, TripState}
import org.apache.spark.streaming.dstream.DStream

trait Adapter {
  def extract(sourceDstream:DStream[String]):DStream[(String,SourceData)]
}

trait SubTrip {
  def aggregate(stream: DStream[(String,SourceData)]): DStream[(String,TripState)]
}