package com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.codec

import com.imggaming.feedbank.codec.{SprayJsonFromStringDecoder, SprayJsonToStringEncoder}
import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.model._

trait $feedname;format="Camel"$InboundEncodings extends $feedname;format="Camel"$InboundJsonProtocol {
  implicit val $feedname;format="Camel"$PacketInDecoder: SprayJsonFromStringDecoder[$feedname;format="Camel"$PacketIn] = {
    new SprayJsonFromStringDecoder[$feedname;format="Camel"$PacketIn]
  }
  implicit val $feedname;format="Camel"$PacketInEncoder: SprayJsonToStringEncoder[$feedname;format="Camel"$PacketIn] = {
    new SprayJsonToStringEncoder[$feedname;format="Camel"$PacketIn]
  }
}

object $feedname;format="Camel"$InboundEncodings extends $feedname;format="Camel"$InboundEncodings
