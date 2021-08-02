package com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.codec

import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.model._

import spray.json._

trait $feedname;format="Camel"$InboundJsonProtocol extends DefaultJsonProtocol {

  implicit val $feedname;format="word,lower"$IdInFormat: RootJsonFormat[$feedname;format="Camel"$IdIn] = jsonFormat1(
    $feedname;format="Camel"$IdIn
  )

  implicit val $feedname;format="word,lower"$PacketInFmt: RootJsonFormat[$feedname;format="Camel"$PacketIn] = {
    jsonFormat2($feedname;format="Camel"$PacketIn)
  }

}

object $feedname;format="Camel"$InboundJsonProtocol extends $feedname;format="Camel"$InboundJsonProtocol
