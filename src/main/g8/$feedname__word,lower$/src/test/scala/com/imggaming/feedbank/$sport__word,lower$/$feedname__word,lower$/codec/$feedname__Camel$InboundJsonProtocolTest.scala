package com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.codec

import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model.{$feedname;format="Camel"$IdIn, $feedname;format="Camel"$PacketIn}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import spray.json._

class $feedname;format="Camel"$InboundJsonProtocolTest
  extends AnyWordSpec
    with Matchers
    with $feedname;format="Camel"$InboundJsonProtocol {

  "$feedname;format="Camel"$InboundJsonProtocol" should {

    "successfully decode an inbound packet" when {

      "the packet has the correct structure" in {
        val json =
          """
            |{
            |  "type": "$feedname;format="camel"$",
            |  "id": { "id":"1one" },
            |  "seqNum": 1
            |}
            |""".stripMargin.parseJson
        val expected = $feedname;format="Camel"$PacketIn($feedname;format="Camel"$IdIn("1one"),1)
        json.convertTo[$feedname;format="Camel"$PacketIn] shouldEqual expected
      }
    }

    "fail to decode the packet" when {

      "the feed type is incorrect" in {
        val json =
          """
            |{
            |  "type": "otherType",
            |  "id": { "id":"1one" },
            |  "seqNum": 1
            |}
            |""".stripMargin.parseJson
        an [DeserializationException] should be thrownBy json.convertTo[$feedname;format="Camel"$PacketIn]
      }

      "the packet ID is improperly formatted" in {
        val json =
          """
            |{
            |  "type": "otherType",
            |  "id": "1one",
            |  "seqNum": 1
            |}
            |""".stripMargin.parseJson
        an [DeserializationException] should be thrownBy json.convertTo[$feedname;format="Camel"$PacketIn]
      }

    }

  }

}
