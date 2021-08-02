package com.imggaming.feedbank.$sport;format="word,lower"$

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

import akka.actor.ActorSystem
import akka.util.Timeout

import com.imggaming.feedbank.config.Parallelism
import com.imggaming.feedbank.metrics.FeedbankMetrics
import com.imggaming.feedbank.service.config.{CleanupConfig, ServiceConfig}
import com.imggaming.feedbank.streaming.{Feedbank, PacketRouter1}
import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.$feedname;format="Camel"$Feed
import com.imggaming.feedbank.$sport;format="word,lower"$.service.$sport;format="Camel"$ServiceActor
import com.imggaming.metrics.Metrics

object Main extends App {
  implicit val system: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContext = system.dispatcher
  implicit val timeout: Timeout = Timeout(3.seconds)
  implicit val serviceConfig: ServiceConfig = ServiceConfig()
  implicit val parallelism: Parallelism = new Parallelism(4)
  implicit val metrics: Metrics = Metrics()
  implicit val feedbankMetrics: FeedbankMetrics = new FeedbankMetrics

  val $feedname;format="camel"$Feed = {
    // FIXME(TEMPLATE): Add a ServiceConfig case class to the template and read local service props
    // from there instead
    val cleanupCfg = CleanupConfig(
      ServiceConfig.defaultConfig.getConfig("service.cleanup.$feedname;format="hyphen,lower"$")
    )
    $feedname;format="Camel"$Feed(
      cleanupCfg.postStartPurgeDelay,
      cleanupCfg.postCompletePurgeDelay
    )
  }

  // FIXME: Don't forget to fill in other feeds if you have multiple
  val packetRouter = new PacketRouter1(
    $feedname;format="Camel"$Feed.AddressIn -> $feedname;format="camel"$Feed.flow
  )
  val feedbank = new Feedbank(packetRouter)

  system.actorOf($sport;format="Camel"$ServiceActor.props(feedbank))
}
