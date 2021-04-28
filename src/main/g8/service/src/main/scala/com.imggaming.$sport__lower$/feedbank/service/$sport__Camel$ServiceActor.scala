package com.imggaming.$sport;format="lower"$.feedbank.service

import scala.concurrent.Future

import akka.{Done, NotUsed}
import akka.actor.Props
import akka.http.scaladsl.server.Directives._
import akka.stream.scaladsl.Sink

import com.imggaming.feedbank.admin.{FeedbankAdmin, FeedbankRouting}
import com.imggaming.feedbank.model._
import com.imggaming.feedbank.service.config.ServiceConfig
import com.imggaming.feedbank.streaming.FeedStreaming
import com.imggaming.feedbank.streaming.FeedbankStreaming.ControlPanel

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

  type CP = FeedStreaming.ControlPanel[$sport;format="Camel"$IdIn, $sport;format="Camel"$PacketIn, NotUsed, NotUsed]

}
