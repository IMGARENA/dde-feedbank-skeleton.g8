package com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.transformer

import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model._
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model.enrichment.Enriched$feedname;format="Camel"$
import com.imggaming.feedbank.streaming.transform.SimpleStatefulTransforming
import com.imggaming.feedbank.streaming.transform.SimpleStatefulTransforming.MessageOut
import com.imggaming.feedbank.model.EnrichedPacket
import scala.collection.immutable
import java.time.Instant

class $feedname;format="Camel"$Transformer
  extends SimpleStatefulTransforming[
    $feedname;format="Camel"$IdIn,
    $feedname;format="Camel"$PacketIn,
    $feedname;format="Camel"$IdOut,
    $feedname;format="Camel"$PacketOut,
    Enriched$feedname;format="Camel"$,
    $feedname;format="Camel"$State
  ] {

    override protected def mapConcat(
      item: EnrichedPacket[
        $feedname;format="Camel"$IdIn,
        $feedname;format="Camel"$PacketIn,
        Enriched$feedname;format="Camel"$
      ]
    )(state: Map[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$State]): (immutable.Iterable[O], Map[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$State]) = {
      (
        Seq(MessageOut($feedname;format="Camel"$PacketOut($feedname;format="Camel"$IdOut(1), 1, Instant.now()))),
        Map(item.packet.id -> $feedname;format="Camel"$State.InitialState)
      )
    }

  }
