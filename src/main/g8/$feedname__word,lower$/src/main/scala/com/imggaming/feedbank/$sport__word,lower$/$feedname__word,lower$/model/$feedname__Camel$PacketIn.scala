package com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.model

import com.imggaming.feedbank.model.PacketIn

// You may change the id type as you need
case class $feedname;format="Camel"$PacketIn(
  id: $feedname;format="Camel"$IdIn,
  seqNum: Int
) extends PacketIn[$feedname;format="Camel"$IdIn]
