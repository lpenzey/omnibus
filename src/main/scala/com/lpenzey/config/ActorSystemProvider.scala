package com.lpenzey.config

import akka.actor.ActorSystem

trait ActorSystemProvider {
  implicit def actorSystem: ActorSystem
}