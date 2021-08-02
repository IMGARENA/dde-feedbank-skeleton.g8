package com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.codec

import spray.json._

import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.model._

trait $feedname;format="Camel"$OutboundJsonProtocol extends DefaultJsonProtocol {

  implicit val $feedname;format="Camel"$IdOutFmt: JsonFormat[$feedname;format="Camel"$IdOut] = jsonFormat1($feedname;format="Camel"$IdOut)

  implicit val $feedname;format="Camel"$PacketOutFmt = jsonFormat2($feedname;format="Camel"$PacketOut)
}

object $feedname;format="Camel"$OutboundJsonProtocol extends $feedname;format="Camel"$OutboundJsonProtocol
