package com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.codec

import com.imggaming.feedbank.codec.SprayJsonFromStringDecoder
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model._

import spray.json._

trait $feedname;format="Camel"$InboundJsonProtocol extends DefaultJsonProtocol {

  implicit val idInFormat: JsonFormat[$feedname;format="Camel"$IdIn] = jsonFormat1($feedname;format="Camel"$IdIn)

  implicit object $feedname;format="Camel"$PacketInFormat extends RootJsonFormat[$feedname;format="Camel"$PacketIn] {
    // Filling in just the read for now as this is the input
    override def read(obj: JsValue): $feedname;format="Camel"$PacketIn = {
      val jsObj = obj.asJsObject
      jsObj.fields.get("type") match {
        case Some(JsString(str)) if str == """$feedname;format="camel"$""" =>
          $feedname;format="Camel"$PacketIn(
            jsObj.fields("id").convertTo[$feedname;format="Camel"$IdIn],
            jsObj.fields("seqNum").convertTo[Int]
          )
        case _ => deserializationError("""Received unexpected JSON in $feedname;format="camel"$ feed""")
      }
    }

    override def write(packet: $feedname;format="Camel"$PacketIn) = ???
  }

  implicit val packetInDecoder: SprayJsonFromStringDecoder[$feedname;format="Camel"$PacketIn] =
    new SprayJsonFromStringDecoder[$feedname;format="Camel"$PacketIn]
}

object $feedname;format="Camel"$InboundJsonProtocol extends $feedname;format="Camel"$InboundJsonProtocol
