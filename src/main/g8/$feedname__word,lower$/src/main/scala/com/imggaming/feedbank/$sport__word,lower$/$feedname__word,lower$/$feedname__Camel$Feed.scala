package com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$

import akka.NotUsed
import akka.actor.ActorRef
import akka.util.Timeout
import com.imggaming.feedbank.codec.{ConnectionStatusEncoder => StatusEncoder}
import com.imggaming.feedbank.codec.{PacketOutEncoder => OutEncoder}
import com.imggaming.feedbank.config.Parallelism
import com.imggaming.feedbank.model.FeedAddress
import com.imggaming.feedbank.streaming._
import com.imggaming.feedbank.streaming.stateful.StatefulFlow
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.codec.$feedname;format="Camel"$InboundJsonProtocol._
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.codec.$feedname;format="Camel"$OutboundJsonProtocol._
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.enrichment.$feedname;format="Camel"$IdResolver
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model._
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model.enrichment.Enriched$feedname;format="Camel"$
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.transformer._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object $feedname;format="Camel"$Feed {
  val AddressIn = FeedAddress("""$sport;format="word-only,lower"$""", """$feedname;format="camel"$""")
  val AddressOut = FeedAddress("""$sport;format="word-only,lower"$""", """$feedname;format="camel"$""")

  def apply(
    enrichmentCache: ActorRef,
    notifier: NotificationDelivery[$feedname;format="Camel"$IdOut, $feedname;format="Camel"$PacketOut, NotUsed]
  )(implicit ec: ExecutionContext, parallelism: Parallelism, timeout: Timeout) = {
    val inDecoder: InDecoder[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn] =
      new InDecoder[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn]()
    val outEncoder: OutEncoder[$feedname;format="Camel"$IdOut, $feedname;format="Camel"$PacketOut] =
      new OutEncoder[$feedname;format="Camel"$IdOut, $feedname;format="Camel"$PacketOut](AddressOut)

    val deduplicator: Deduplicator[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn] =
      new Deduplicator[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn]
    val stash: Stash[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn] =
      new Stash[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn]
    val enricher: Enricher[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn, Enriched$feedname;format="Camel"$] =
      new Enricher[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn, Enriched$feedname;format="Camel"$](enrichmentCache)
    val transformer: $feedname;format="Camel"$Transformer =
      new $feedname;format="Camel"$Transformer()
    val packetEncoder: PacketOutEncoder[$feedname;format="Camel"$IdOut, $feedname;format="Camel"$PacketOut] =
      new PacketOutEncoder(outEncoder)

    val packetStream: PacketStream[
      $feedname;format="Camel"$IdIn,
      $feedname;format="Camel"$PacketIn,
      $feedname;format="Camel"$IdOut,
      $feedname;format="Camel"$PacketOut,
      Enriched$feedname;format="Camel"$,
      StatefulFlow.AdminIO[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$State],
      NotUsed
    ] = new PacketStream(
      deduplicator = deduplicator,
      stash = stash,
      enricher = enricher,
      transformer = transformer,
      notifier = notifier,
      packetEncoder = packetEncoder
    )

    val connStatusStream = new ConnectionStatusStream(
      new ConnectionMonitor(tickInterval = 10.seconds, alarmAfter = 30.seconds),
      new ConnectionStatusIdResolver(enrichmentCache, $feedname;format="Camel"$IdResolver),
      new ConnectionStatusEncoder(new StatusEncoder(AddressOut))
    )

    new Feed(inDecoder, packetStream, connStatusStream, enrichmentCache)
  }

}
