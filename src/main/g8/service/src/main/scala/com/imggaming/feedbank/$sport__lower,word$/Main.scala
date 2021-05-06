package com.imggaming.feedbank.$sport;format="word-only,lower"$

import akka.actor.ActorSystem
import akka.util.Timeout
import com.imggaming.feedbank.config.Parallelism
import com.imggaming.feedbank.service.FeedbankRunner
import com.imggaming.feedbank.service.config.ServiceConfig
import com.imggaming.feedbank.streaming.Feedbank
import com.imggaming.feedbank.streaming.PacketRouter1
import com.imggaming.feedbank.streaming.notification.NoopNotifier
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.$feedname;format="Camel"$Feed
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model.$feedname;format="Camel"$IdOut
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model.$feedname;format="Camel"$PacketOut
import com.imggaming.feedbank.$sport;format="word-only,lower"$.service.$sport;format="Camel"$ServiceActor

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object Main extends App {

  implicit val system: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContext = system.dispatcher
  implicit val timeout: Timeout = Timeout(3.seconds)
  implicit val serviceConfig: ServiceConfig = ServiceConfig()
  implicit val parallelism: Parallelism = new Parallelism(4)

  val notifier: NoopNotifier[$feedname;format="Camel"$IdOut, $feedname;format="Camel"$PacketOut] =
    new NoopNotifier[$feedname;format="Camel"$IdOut, $feedname;format="Camel"$PacketOut]

  // TODO use no-op enrichment

  val feed = $feedname;format="Camel"$Feed(system.deadLetters, notifier) // TODO change deadLetters to an enrichment actor

  val packetRouter =
    new PacketRouter1(
      $feedname;format="Camel"$Feed.AddressIn -> feed.flow
    )
  val feedbank = new Feedbank(packetRouter)
  val runner = FeedbankRunner(feedbank)

  system.actorOf($sport;format="Camel"$ServiceActor.props(runner))
}
