package com.imggaming.feedbank.$sport;format="word-only,lower"$

import akka.NotUsed
import akka.actor.ActorSystem
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model._
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model.enrichment.Enriched$feedname;format="Camel"$
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.transformer.$feedname;format="Camel"$Transformer
import com.imggaming.feedbank.$sport;format="word-only,lower"$.service.$sport;format="Camel"$ServiceActor
import com.imggaming.feedbank.model.FeedAddress
import com.imggaming.feedbank.service.FeedbankRunner
import com.imggaming.feedbank.service.config.ServiceConfig
import com.imggaming.feedbank.streaming.Feed
import com.imggaming.feedbank.streaming.Feedbank
import com.imggaming.feedbank.streaming.PacketRouter1

import scala.concurrent.ExecutionContext

object Main extends App {

  implicit val system: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContext = system.dispatcher
  implicit val serviceConfig: ServiceConfig = ServiceConfig()

  // Your runners and service actors here...

  val feed: Feed[
    $feedname;format="Camel"$IdIn,
    $feedname;format="Camel"$PacketIn,
    $feedname;format="Camel"$IdOut,
    $feedname;format="Camel"$PacketOut,
    Enriched$feedname;format="Camel"$,
    $feedname;format="Camel"$Transformer,
    NotUsed
  ] = ???
  
  val packetRouter =
    new PacketRouter1(
      FeedAddress("""$sport;format="word-only,lower"$""", """$feedname;format="word-only,lower"$""") -> feed.flow
    )
  val feedbank = new Feedbank(packetRouter)
  val runner = FeedbankRunner(feedbank)

  system.actorOf($sport;format="Camel"$ServiceActor.props(runner))
}
