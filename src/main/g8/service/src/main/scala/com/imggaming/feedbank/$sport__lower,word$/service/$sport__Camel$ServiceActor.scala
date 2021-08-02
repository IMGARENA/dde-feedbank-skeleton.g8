package com.imggaming.feedbank.$sport;format="word,lower"$.service

import scala.concurrent.Future

import akka.{Done, NotUsed}
import akka.actor.Props

import com.imggaming.feedbank.admin._
import com.imggaming.feedbank.model.FeedAddress
import com.imggaming.feedbank.service.ServiceActor
import com.imggaming.feedbank.service.config.ServiceConfig
import com.imggaming.feedbank.service.sync.SyncWorker
import com.imggaming.feedbank.state.ops.TransformerQuerying
import com.imggaming.feedbank.streaming._
import com.imggaming.feedbank.streaming.FeedbankStreaming.ControlPanel
import com.imggaming.feedbank.streaming.stateful.StatefulFlow.AdminIO
import com.imggaming.feedbank.$sport;format="word,lower"$.service.codec.AdminEncodings
import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.$feedname;format="Camel"$Feed
import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.model._
import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.transformer.$feedname;format="Camel"$Transformer


class $sport;format="Camel"$ServiceActor(
  feedbank: FeedbankStreaming[$sport;format="Camel"$ServiceActor.CP]
)(implicit cfg: ServiceConfig)
  extends ServiceActor(feedbank)(cfg)
  with AdminEncodings {

  import $sport;format="Camel"$ServiceActor._

  override protected def createAdmin(
    cp: ControlPanel[CP],
    syncStatus: Future[Done]
  ): FeedbankRouting = {
    implicit val transformerClient: TransformerQuerying[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$Transformer.State] = {
      implicit val io = cp.feedControls.packet.transformer
      $feedname;format="Camel"$Feed.transformerClient
    }

    val feedRoute = new FeedRoute[
      $feedname;format="Camel"$IdIn,
      $feedname;format="Camel"$PacketIn,
      Unit,
      AdminIO[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$Transformer.State],
      $feedname;format="Camel"$Transformer.State,
      NotUsed
    ](cp.feedControls)

    // FIXME: Don't forget to add additional feeds if you have more than one
    val allFeeds = new AllFeedsRoute(Map(
      $feedname;format="Camel"$Feed.AddressIn -> feedRoute.routes
    ))
    val service = new ServiceRoute(cp.killSwitch, cp.inputValve, syncStatus)

    new FeedbankAdmin(allFeeds.routes, service.routes)
  }


  override protected def syncWorkers(cp: ControlPanel[CP]): Seq[(FeedAddress, Props)] = {
    implicit val backoff = cfg.sync.verifyBackoff

    // FIXME: Don't forget to add additional feeds if you have more than one
    implicit val io = cp.feedControls.packet.stash
    val w = SyncWorker.props[MatchDetailsIdIn, MatchDetailsPacketIn](MatchDetailsFeed.AddressIn)

    List($feedname;format="Camel"$Feed.AddressIn -> w)
  }

}

object $sport;format="Camel"$ServiceActor {

  def props(
    feedbank: FeedbankStreaming[$sport;format="Camel"$ServiceActor.CP]
  )(implicit cfg: ServiceConfig): Props = Props(new $sport;format="Camel"$ServiceActor(feedbank))

  // FIXME: If you have multiple feeds, you should redefine this as
  // type CP = (FeedStreaming.ControlPanel[...], FeedStreaming.ControlPanel[...], ...) and update
  // methods of the actor accordingly
  type CP = FeedStreaming.ControlPanel[
    $feedname;format="Camel"$IdIn,
    $feedname;format="Camel"$PacketIn,
    AdminIO[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$Transformer.State],
    NotUsed
  ]

}
