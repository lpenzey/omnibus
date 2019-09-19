package com.lpenzey.actors
import pdi.jwt.{JwtAlgorithm, JwtClaim, JwtSprayJson}
import java.time.Instant

import akka.actor.{Actor, ActorLogging, Props}
import com.lpenzey.dao.UsersDao
import com.lpenzey.models.{User, Users}
import com.lpenzey.helpers.JsonSupport
import spray.json.JsObject

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object RegisterUserActor {
  final case object GetUsers
  final case class CreateToken(user: User)
  final case class CreateUser(user: User)
  final case class GetUser(name: String)
  final case class GetFavorites(token: String)
  final case class DeleteUser(name: String)
  final case class ActionPerformed(action: String)
  def props: Props = Props[RegisterUserActor]
}

class RegisterUserActor extends JsonSupport with Actor with ActorLogging {
  import RegisterUserActor._
  import context.dispatcher

  def receive: Receive = {
    case GetUsers =>
      val getUserSender = sender
      val allUsers = UsersDao.getUsers
      allUsers.onComplete {
        case Success(usr) => getUserSender ! Users(usr)
        case Failure(failureUsr) =>  println(failureUsr.toString)
      }

    case CreateUser(user) =>
      UsersDao.createUser(user)
      sender() ! ActionPerformed(s"User ${user.name} created.")

    case GetUser(name) =>
      val userSender = sender
      val user: Future[Option[User]] = UsersDao.findUserByName(name)
      user.onComplete {
        case Success(usr) => userSender ! usr.get.copy()
        case Failure(failureUsr) => userSender ! failureUsr
      }

    case DeleteUser(name) =>
      val deluser = UsersDao.deleteUser(name)
      val delSender = sender
      deluser.onComplete {
        case Success(delusr) => delSender ! ActionPerformed(s"User $name deleted")
        case Failure(unDeletedUser) => delSender ! unDeletedUser
      }

    case CreateToken(user) =>
      val tokenSender = sender
      val expiration = Option(Instant.now.plusSeconds(40000).getEpochSecond)
      val issuedAt =  Option(Instant.now.getEpochSecond)
      val userName: String = user.name
      val userId: String = user.id.toString
      val claim = JwtClaim(s"""{"name":"$userName","id":"$userId"}""", None, None, None, expiration, None, issuedAt)
      val key = System.getenv("JWT_SECRET")
      val algo = JwtAlgorithm.HS256
      val token: String = JwtSprayJson.encode(claim, key, algo)

      tokenSender ! token

    case GetFavorites(token) =>
      val favSender = sender
      val key = System.getenv("JWT_SECRET")

      val isAuthed: Try[JsObject] = JwtSprayJson.decodeJson(token, key, Seq(JwtAlgorithm.HS256))
      if (isAuthed.isSuccess) {
        favSender ! "{success:favorites}"
      } else {
        favSender ! "{failure}"
      }
   }
}