package com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.codec

import spray.json._

import com.imggaming.feedbank.SpecKit
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model.{$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn}

class $feedname;format="Camel"$InboundJsonProtocolSpec
  extends SpecKit
  with $feedname;format="Camel"$InboundJsonProtocol {

  "Inbound JSON data" should "be deserialised correctly" in {
    val json = {
      """
      {
        "id": { "id": "1one" },
        "seqNum": 1
      }
      """.parseJson
    }
    val expected = $feedname;format="Camel"$PacketIn($feedname;format="Camel"$IdIn("1one"),1)
    json.convertTo[$feedname;format="Camel"$PacketIn] shouldEqual expected
  }

  it should "fail to deserialise an invalid packet" in {
    val json = {
      """
      {
        "id": "1one",
        "seqNum": 1
      }
      """.parseJson
    }
    a[DeserializationException] should be thrownBy json.convertTo[$feedname;format="Camel"$PacketIn]
  }
}
