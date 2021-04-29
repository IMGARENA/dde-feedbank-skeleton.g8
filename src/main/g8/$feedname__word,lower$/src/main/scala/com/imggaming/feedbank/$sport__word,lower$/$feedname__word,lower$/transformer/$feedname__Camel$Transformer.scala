package com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.transformer

import akka.NotUsed

import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model._
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model.enrichment.Enriched$feedname;format="Camel"$
import com.imggaming.feedbank.streaming.Transforming

class $feedname;format="Camel"$Transformer
  extends Transforming[
    $feedname;format="Camel"$IdIn,
    $feedname;format="Camel"$PacketIn,
    $feedname;format="Camel"$IdOut,
    $feedname;format="Camel"$PacketOut,
    Enriched$feedname;format="Camel"$,
    NotUsed
  ] {

    override def shape = ???

}
