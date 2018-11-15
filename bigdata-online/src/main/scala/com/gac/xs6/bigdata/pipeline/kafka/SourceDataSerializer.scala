package com.gac.xs6.bigdata.pipeline.kafka

import com.gac.xs6.bigdata.model.SourceData
import kafka.serializer.{Decoder, Encoder}
import kafka.utils.VerifiableProperties
import org.json4s.DefaultFormats
import org.json4s.native.Serialization.{read, write}
import com.gac.xs6.bigdata.util.Json4sHelper._

/**
  * Created by azhe on 18-4-3.
  */
class SourceDataSerializer(veriProps: VerifiableProperties) extends Encoder[SourceData] with Decoder[SourceData] {
  implicit val formats = DefaultFormats + StringToBigDecimal + StringToInt + StringToDouble + StringToInstant + StringToLong

  override def toBytes(t: SourceData): Array[Byte] = write(t).getBytes

  override def fromBytes(bytes: Array[Byte]): SourceData = read[SourceData](new String(bytes))
}

