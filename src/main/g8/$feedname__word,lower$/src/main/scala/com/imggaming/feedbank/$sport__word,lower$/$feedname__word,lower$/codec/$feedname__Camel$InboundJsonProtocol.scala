package com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.codec

import com.imggaming.feedbank.codec.SprayJsonFromStringDecoder
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model._

import spray.json._

trait $feedname;format="Camel"$InboundJsonProtocol extends DefaultJsonProtocol {

  implicit val idInFormat: JsonFormat[$feedname;format="Camel"$IdIn] = ???

  implicit object $feedname;format="Camel"$PacketInFormat extends RootJsonFormat[$feedname;format="Camel"$PacketIn] {
    override def read(obj: JsValue): $feedname;format="Camel"$PacketIn = ???

    override def write(packet: $feedname;format="Camel"$PacketIn) = ???
  }

  implicit val packetInDecoder: SprayJsonFromStringDecoder[$feedname;format="Camel"$PacketIn] =
    new SprayJsonFromStringDecoder[$feedname;format="Camel"$PacketIn]
}

object $feedname;format="Camel"$InboundJsonProtocol extends $feedname;format="Camel"$InboundJsonProtocol
