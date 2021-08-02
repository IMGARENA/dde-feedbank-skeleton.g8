package com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.codec

import spray.json._

import com.imggaming.feedbank.SpecKit
import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.model.{$feedname;format="Camel"$IdOut, $feedname;format="Camel"$PacketOut}

class $feedname;format="Camel"$OutboundJsonProtocolSpec
  extends SpecKit
  with $feedname;format="Camel"$OutboundJsonProtocol {

  "Outbound JSON data" should "be serialised correctly" in {
    val expected = {
      """
      {
        "id": {
          "id": "1one"
        },
        "seqNum": 1
      }
      """
    }

    val actual = $feedname;format="Camel"$PacketOut(
      id = $feedname;format="Camel"$IdOut("1one"),
      seqNum = 1,
    )

    actual.toJson should be(expected.parseJson)
  }


}
