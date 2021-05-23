package com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.codec

import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model.{$feedname;format="Camel"$IdOut, $feedname;format="Camel"$PacketOut}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import spray.json._

import java.time.Instant

class $feedname;format="Camel"$OutboundJsonProtocolTest
  extends AnyWordSpec
    with Matchers
    with $feedname;format="Camel"$OutboundJsonProtocol {

  "$feedname;format="Camel"$OutboundJsonProtocol" should {

    "successfully encode an outbound packet" when {

      "the encoder in called" in {

        val expected =
          """{
            |  "id": {
            |    "id": "1one"
            |  },
            |  "seqNum": 1,
            |  "timestamp": "1970-01-01T00:00:00.000Z",
            |  "type": "$feedname;format="camel"$"
            |}""".stripMargin

        val outboundJson = $feedname;format="Camel"$PacketOut(
          id = $feedname;format="Camel"$IdOut("1one"),
          seqNum = 1,
          timestamp = Instant.EPOCH
        ).toJson.prettyPrint

        outboundJson shouldEqual expected
      }

    }

  }

}
