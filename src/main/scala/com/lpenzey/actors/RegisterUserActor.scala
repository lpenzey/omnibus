package com.lpenzey.actors
import pdi.jwt.{JwtAlgorithm, JwtClaim, JwtSprayJson}
import java.time.Instant

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import com.lpenzey.dao.UsersDao
import com.lpenzey.models.{User, Users}
import com.lpenzey.helpers.JsonSupport

import scala.util.{Failure, Success}

object RegisterUserActor {

  final case object GetUsers
  final case class CreateToken(user: User)
  final case class CreateUser(user: User)
  final case class GetUser(name: String)
  final case class DeleteUser(name: String)
  final case class ActionPerformed(action: String)

  def props: Props = Props[RegisterUserActor]
}

class RegisterUserActor extends JsonSupport with Actor with ActorLogging {
  import RegisterUserActor._
  import context.dispatcher

  def receive: Receive = {
    case GetUsers =>
      val mysender = sender
      val allUsers = UsersDao.getUsers
      allUsers.onComplete {
        case Success(usr) => mysender ! Users(usr)
        case Failure(failureUsr) =>  println(failureUsr.toString)
      }
    case CreateUser(user) =>
      UsersDao.createUser(user)
      sender() ! ActionPerformed(s"User ${user.name} created.")

    case GetUser(name) =>
      val user = UsersDao.findUserByName(name)
      val userSender = sender
      user.onComplete {
        case Success(usr) => userSender ! usr.get.copy()
        case Failure(failureUsr) => println(s"$name user not found")
      }
    case DeleteUser(name) =>
      val deluser = UsersDao.deleteUser(name)
      val delSender = sender
      deluser.onComplete {
        case Success(delusr) => delSender ! ActionPerformed(s"User $name deleted")
        case Failure(unDeletedUser) => println(unDeletedUser)
      }
    case CreateToken(user) =>
      val tokenSender = sender
      val expiration = Option(Instant.now.plusSeconds(40000).getEpochSecond)
      val issuedAt =  Option(Instant.now.getEpochSecond)
      val jsonUser = user
      val claim = JwtClaim(user.toString, None, None, None, expiration, None, issuedAt)
      val key = "secretKey"
      val algo = JwtAlgorithm.HS256
      val token = JwtSprayJson.encode(claim, key, algo)

      tokenSender ! token
  }
}