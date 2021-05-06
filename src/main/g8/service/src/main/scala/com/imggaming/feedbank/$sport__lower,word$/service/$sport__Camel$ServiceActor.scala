package com.imggaming.feedbank.$sport;format="word-only,lower"$.service

import akka.Done
import akka.NotUsed
import akka.actor.Props
import akka.http.scaladsl.server.Directives._
import akka.stream.scaladsl.Sink
import com.imggaming.feedbank.admin.FeedbankAdmin
import com.imggaming.feedbank.admin.FeedbankRouting
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model.$feedname;format="Camel"$IdIn
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model.$feedname;format="Camel"$PacketIn
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.transformer.$feedname;format="Camel"$State
import com.imggaming.feedbank.service.FeedbankRunner
import com.imggaming.feedbank.service.ServiceActor
import com.imggaming.feedbank.service.config.ServiceConfig
import com.imggaming.feedbank.streaming.FeedStreaming
import com.imggaming.feedbank.streaming.FeedbankStreaming.ControlPanel
import com.imggaming.feedbank.streaming.stateful.StatefulFlow

import scala.concurrent.Future

class $sport;format="Camel"$ServiceActor(
  feedbankRunner: FeedbankRunner[Sink[String, NotUsed], $sport;format="Camel"$ServiceActor.CP, Future[Done]]
)(implicit cfg: ServiceConfig) extends ServiceActor(feedbankRunner)(cfg) {
  import $sport;format="Camel"$ServiceActor._

  /**
   * Stub out the admin site with some dummy paths only
   */
  override protected def createAdmin(cp: ControlPanel[CP]): FeedbankRouting = {
    val allFeedsRoute = path("dummy-allfeeds") { get { complete(200 -> "OK") } }
    val serviceRoutes = path("dummy-service") { get { complete(200 -> "OK") } }
    new FeedbankAdmin(allFeedsRoute, serviceRoutes)
  }

}

object $sport;format="Camel"$ServiceActor {

  def props(feedbankRunner: FeedbankRunner[Sink[String, NotUsed], CP, Future[Done]])(
    implicit cfg: ServiceConfig
  ): Props = Props(new $sport;format="Camel"$ServiceActor(feedbankRunner))

  type CP = FeedStreaming.ControlPanel[
    $feedname;format="Camel"$IdIn,
    $feedname;format="Camel"$PacketIn,
    StatefulFlow.AdminIO[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$State],
    NotUsed
  ]

}
