package com.imggaming.feedbank.$sport;format="word-only,lower"$.$feedname;format="word-only,lower"$.model

import com.imggaming.feedbank.model.IdIn

// A simple / common ID type; you can make this whatever you want
// FIXME: Consider also whether this ID can be reused between feeds and moved to the common
// submodule, or if it belongs only to this feed. For example, match details, match stats, and
// match highlights feeds may all refer to a match and therefore all have a common MatchIdIn type
final case class $feedname;format="Camel"$IdIn(id: String) extends IdIn
