package com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.model

import com.imggaming.feedbank.model.PacketOut

case class $feedname;format="Camel"$PacketOut(
  id: $feedname;format="Camel"$IdOut,
  seqNum: Int
) extends PacketOut[$feedname;format="Camel"$IdOut]
