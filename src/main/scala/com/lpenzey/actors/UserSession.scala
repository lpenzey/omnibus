package com.lpenzey.actors

import akka.actor.{Actor, ActorLogging, Props}
import com.lpenzey.models.User
import com.lpenzey.helpers.{JsonSupport, TokenService}

object UserSession {
  final case class CreateToken(user: User)
  final case class ActionPerformed(action: String)
  def props: Props = Props[UserSession]
}

class UserSession extends JsonSupport with Actor with ActorLogging with TokenService  {
  import RegisterUser._
  val key: String = System.getenv("JWT_SECRET")

  def receive: Receive = {

    case CreateToken(user) =>
      val tokenSender = sender

      val token: String = createToken(user)

      tokenSender ! token
  }
}