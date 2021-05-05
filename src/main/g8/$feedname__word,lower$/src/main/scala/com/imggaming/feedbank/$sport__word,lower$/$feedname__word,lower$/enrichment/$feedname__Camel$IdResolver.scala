package com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.enrichment

import com.imggaming.feedbank.enrichment.IdResolution
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model.$feedname;format="Camel"$IdIn
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model.$feedname;format="Camel"$IdOut
import com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model.enrichment.Enriched$feedname;format="Camel"$
import com.imggaming.feedbank.model.EnrichedId

object $feedname;format="Camel"$IdResolver
  extends IdResolution[$feedname;format="Camel"$IdIn, $feedname;format="Camel"$IdOut, Enriched$feedname;format="Camel"$] {

  override def toOut(id: EnrichedId[$feedname;format="Camel"$IdIn, Enriched$feedname;format="Camel"$]): $feedname;format="Camel"$IdOut =
    id.enrichmentData.id
  
}
