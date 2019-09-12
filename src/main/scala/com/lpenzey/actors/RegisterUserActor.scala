package com.lpenzey.actors

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import com.lpenzey.JsonSupport
import com.lpenzey.dao.UsersDao
import com.lpenzey.models.{User, Users}

import scala.util.{Failure, Success}

object RegisterUserActor {
  final case class ActionPerformed(action: String)
  final case object GetUsers
  final case class CreateUser(user: User)
  final case class GetUser(name: String)
  final case class DeleteUser(name: String)

  def props: Props = Props[UserRegistryActor]
}

class UserRegistryActor extends JsonSupport with Actor with ActorLogging {
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
        case Success(usr) => userSender ! usr
        case Failure(failureUsr) => println(s"$name user not found")
      }
    case DeleteUser(name) =>
      val deluser = UsersDao.deleteUser(name)
      val delSender = sender
      deluser.onComplete {
        case Success(delusr) => delSender ! ActionPerformed(s"User $name deleted")
        case Failure(unDeletedUser) => println(unDeletedUser)
      }
  }
}