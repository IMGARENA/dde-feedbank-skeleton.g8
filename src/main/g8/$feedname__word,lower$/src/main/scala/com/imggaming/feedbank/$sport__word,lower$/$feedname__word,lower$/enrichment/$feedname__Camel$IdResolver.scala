package com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.enrichment

import com.imggaming.feedbank.enrichment.IdResolution
import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.model.$feedname;format="Camel"$IdIn
import com.imggaming.feedbank.$sport;format="word,lower"$.$feedname;format="word,lower"$.model.$feedname;format="Camel"$IdOut
import com.imggaming.feedbank.model.EnrichedId

object $feedname;format="Camel"$IdResolver
  extends IdResolution[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$IdOut, Unit] {

  override def toOut(id: EnrichedId[$feedname;format="Camel"$IdIn, Unit]): $feedname;format="Camel"$IdOut = {
    // FIXME: You'll need to map your IDs using your enrichment data
    $feedname;format="Camel"$IdOut(id.id.id)
  }
}
