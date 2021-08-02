package com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.duration._

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.util.Timeout

import com.imggaming.feedbank.codec.{
  ConnectionStatusEncoder => StatusEncoder,
  PacketOutEncoder => OutEncoder
}
import com.imggaming.feedbank.config.Parallelism
import com.imggaming.feedbank.enrichment.NoopEnricher
import com.imggaming.feedbank.metrics.{FeedMetrics, StageName}
import com.imggaming.feedbank.model.FeedAddress
import com.imggaming.feedbank.state.EnrichmentCache
import com.imggaming.feedbank.state.AdminMessages.{Request, Response}
import com.imggaming.feedbank.state.ops._
import com.imggaming.feedbank.streaming._
import com.imggaming.feedbank.streaming.notification.NoopNotifier
import com.imggaming.feedbank.streaming.stateful.StatefulFlow.AdminIO
import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.codec.$feedname;format="Camel"$InboundEncodings
import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.codec.$feedname;format="Camel"$OutboundEncodings
import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.enrichment.$feedname;format="Camel"$IdResolver
import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.model._
import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.transformer._
import com.imggaming.metrics.Metrics

object $feedname;format="Camel"$Feed
  extends $feedname;format="Camel"$InboundEncodings
  with $feedname;format="Camel"$OutboundEncodings {

  val AddressIn = FeedAddress("$sport;format="word,lower"$", "$feedname;format="camel"$")
  val AddressOut = FeedAddress("$sport;format="word,lower"$", "$feedname;format="camel"$")

  def transformerClient(
    implicit io: AdminIO[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$Transformer.State],
    mat: Materializer,
    timeout: Timeout
  ): TransformerQuerying[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$Transformer.State] = {
    new AdminIOTransformerClient[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$Transformer.State]
  }

  def apply(
    cleanupPostStartPurgeDelay: Option[FiniteDuration],
    cleanupPostCompletePurgeDelay: Option[FiniteDuration]
  )(
    implicit ec: ExecutionContext,
    parallelism: Parallelism,
    system: ActorSystem,
    timeout: Timeout,
    metrics: Metrics
  ): Feed[
    $feedname;format="Camel"$IdIn,
    $feedname;format="Camel"$PacketIn,
    $feedname;format="Camel"$IdOut,
    $feedname;format="Camel"$PacketOut,
    Unit,
    AdminIO[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$Transformer.State],
    NotUsed
  ] = {
    implicit val m = new FeedMetrics()(metrics, AddressIn)
    implicit val em = m.enrichment
    implicit val cm = m.connectionMonitor

    val reconstructor = new NoopReconstructor[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn]()(
      m.forStage(StageName.Reconstructor)
    )
    val deduplicator = {
      new Deduplicator[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn]()(
        m.forStage(StageName.Deduplicator)
      )
    }

    val stash = {
      new Stash[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn]()(
        m.forStage(StageName.Stash)
      )
    }

    // FIXME: Replace with a schedule enricher when you have a schedule endpoint / model prepared
    val enrichmentCache = system.actorOf(
      EnrichmentCache.props(new NoopEnricher[$feedname;format="Camel"$IdIn])
    )
    val enricher = {
      new Enricher[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn, Unit](enrichmentCache)
    }
    val notifier = new NoopNotifier[$feedname;format="Camel"$IdOut, $feedname;format="Camel"$PacketOut]
    val transformer: $feedname;format="Camel"$Transformer = {
      new $feedname;format="Camel"$Transformer()(m.forStage(StageName.Transformer))
    }
    val cleanupReporter = {
      // FIXME(TEMPLATE): Temporary workaround for DDE-1067; transformer will not be cleaned up
      // This is essentially a copy of UnsupportedTransformerClient but with a State type
      implicit val transformerClient = new TransformerQuerying[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$Transformer.State] {
        override def query(
          q: Request[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$Transformer.State]
        ): Future[Response[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$Transformer.State]] = {
          Future.failed(new UnsupportedComponentException)
        }
      }
      val props = CleanupScheduler.props(cleanupPostStartPurgeDelay, cleanupPostCompletePurgeDelay)
      new CleanupReporter[$feedname;format="Camel"$IdIn](system.actorOf(props))
    }
    val packetEncoder = {
      new PacketOutEncoder(new OutEncoder[$feedname;format="Camel"$IdOut, $feedname;format="Camel"$PacketOut](AddressOut))
    }

    val decoder: InDecoder[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn] = {
      new InDecoder[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn]()
    }

    val packetStream: PacketStreaming[
      $feedname;format="Camel"$IdIn,
      $feedname;format="Camel"$PacketIn,
      $feedname;format="Camel"$IdOut,
      $feedname;format="Camel"$PacketOut,
      Unit,
      AdminIO[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$Transformer.State],
      NotUsed
    ] = new PacketStream(
      reconstructor = reconstructor,
      deduplicator = deduplicator,
      stash = stash,
      enricher = enricher,
      transformer = transformer,
      notifier = notifier,
      cleanupReporter = cleanupReporter,
      packetEncoder = packetEncoder
    )

    val connStatusStream = new ConnectionStatusStream(
      new ConnectionMonitor(tickInterval = 10.seconds, alarmAfter = 30.seconds)(
        m.forStage(StageName.ConnectionMonitor),
        implicitly
      ),
      new ConnectionStatusIdResolver(enrichmentCache, $feedname;format="Camel"$IdResolver),
      new ConnectionStatusEncoder(new StatusEncoder(AddressOut))
    )

    new Feed(decoder, packetStream, connStatusStream, enrichmentCache)
  }

}
