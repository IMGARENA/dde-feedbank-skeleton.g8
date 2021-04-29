package com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model

import com.imggaming.feedbank.model.PacketOut

// You may change the id type as you need
final case class $feedname;format="Camel"$PacketOut(
  id: $feedname;format="Camel"$IdOut,
  seqNum: Int
) extends PacketOut[$feedname;format="Camel"$IdOut]
