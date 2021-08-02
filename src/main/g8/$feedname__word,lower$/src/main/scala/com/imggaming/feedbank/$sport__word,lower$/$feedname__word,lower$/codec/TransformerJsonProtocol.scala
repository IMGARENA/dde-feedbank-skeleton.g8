package com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.codec

import spray.json._

import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.transformer.$feedname;format="Camel"$Transformer.State

/**
 * JSON encodings for transformer state, primarily required to fulfil admin requests
 */
trait TransformerJsonProtocol extends DefaultJsonProtocol with $feedname;format="Camel"$OutboundJsonProtocol {
  implicit val $feedname;format="camel"$StateFmt = jsonFormat1(State)
}

object TransformerJsonProtocol extends TransformerJsonProtocol
