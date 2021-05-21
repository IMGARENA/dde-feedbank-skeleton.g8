package com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.codec

import com.imggaming.feedbank.admin.serialisation.TimeJsonProtocol
import com.imggaming.feedbank.codec._
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model._
import spray.json._

trait $feedname;format="Camel"$OutboundJsonProtocol
  extends TimeJsonProtocol {

  implicit val idOutFormat: JsonFormat[$feedname;format="Camel"$IdOut] = jsonFormat1($feedname;format="Camel"$IdOut)

  implicit object $feedname;format="Camel"$PacketOutFormat extends RootJsonFormat[$feedname;format="Camel"$PacketOut] {
    override def read(json: JsValue): $feedname;format="Camel"$PacketOut = ???

    // Filling in just write for now as this is the output
    override def write(obj: $feedname;format="Camel"$PacketOut): JsValue = {
      JsObject(
        "type" -> JsString("""$feedname;format="camel"$"""),
        "id" -> obj.id.toJson,
        "seqNum" -> JsNumber(obj.seqNum),
        "timestamp" -> obj.timestamp.toJson
      )
    }
  }

  implicit val packetOutEncoder: ToStringEncoding[$feedname;format="Camel"$PacketOut] =
    new SprayJsonToStringEncoder[$feedname;format="Camel"$PacketOut]
}

object $feedname;format="Camel"$OutboundJsonProtocol extends $feedname;format="Camel"$OutboundJsonProtocol
