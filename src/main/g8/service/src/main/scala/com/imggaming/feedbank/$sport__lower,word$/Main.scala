package com.imggaming.feedbank.$sport;format="word-only,lower"$

import akka.actor.ActorSystem

import com.imggaming.feedbank.service.config.ServiceConfig

import scala.concurrent.ExecutionContext

object Main extends App {

  implicit val system: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContext = system.dispatcher
  implicit val serviceConfig: ServiceConfig = ServiceConfig()

  // Your runners and service actors here...
}
