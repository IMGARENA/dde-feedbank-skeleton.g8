package com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.transformer

import scala.collection.immutable

import com.imggaming.feedbank.metrics.StageMetrics
import com.imggaming.feedbank.model.EnrichedPacket
import com.imggaming.feedbank.streaming.transform.SimpleStatefulTransforming
import com.imggaming.feedbank.streaming.transform.SimpleStatefulTransforming.MessageOut
import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.model._

class $feedname;format="Camel"$Transformer()(
  override protected implicit val metrics: StageMetrics
)
  extends SimpleStatefulTransforming[
    $feedname;format="Camel"$IdIn,
    $feedname;format="Camel"$PacketIn,
    $feedname;format="Camel"$IdOut,
    $feedname;format="Camel"$PacketOut,
    Unit,
    $feedname;format="Camel"$Transformer.State
  ] {

  import $feedname;format="Camel"$Transformer._

  private def wrapPacket(p: MatchDetailsPacketOut): MessageOut[
    $feedname;format="Camel"$IdIn,
    $feedname;format="Camel"$PacketIn,
    $feedname;format="Camel"$IdOut,
    $feedname;format="Camel"$PacketOut,
    Unit
  ] = MessageOut(p)

  override protected def mapConcat(
    item: EnrichedPacket[
      $feedname;format="Camel"$IdIn,
      $feedname;format="Camel"$PacketIn,
      Unit
    ]
  )(state: Map[$feedname;format="Camel"$IdIn, State]): (
    immutable.Iterable[O],
    Map[$feedname;format="Camel"$IdIn, State]
  ) = {
    // FIXME: This is where the bulk of your logic will go
    val idIn = item.packet.id
    val seqNum = item.packet.seqNum

    val messages = List($feedname;format="Camel"$PacketOut($feedname;format="Camel"$IdOut(idIn.id), seqNum))
    val currentState = state.getOrElse(idIn, State())
    val updatedState = state + (idIn -> currentState.copy(currentState.messagesOut ++ messages))

    messages.map(wrapPacket) -> updatedState
  }

}

object $feedname;format="Camel"$Transformer {
  // FIXME: Decide what sort of state you need to keep in your transformer. Usually this will at
  // least include messages in the outgoing feed to save to schedule upon completion.
  case class State(messagesOut: List[$feedname;format="Camel"$PacketOut] = Nil)
}
