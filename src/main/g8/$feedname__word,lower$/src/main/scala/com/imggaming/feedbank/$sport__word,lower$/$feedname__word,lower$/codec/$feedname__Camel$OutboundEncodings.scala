package com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.codec

import com.imggaming.feedbank.codec._
import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.model._

trait $feedname;format="Camel"$OutboundEncodings extends $feedname;format="Camel"$OutboundJsonProtocol {
  implicit val $feedname;format="camel"$PacketOutEncoder: ToStringEncoding[$feedname;format="Camel"$PacketOut] =
    new SprayJsonToStringEncoder[$feedname;format="Camel"$PacketOut]
}

object $feedname;format="Camel"$OutboundEncodings extends $feedname;format="Camel"$OutboundEncodings
