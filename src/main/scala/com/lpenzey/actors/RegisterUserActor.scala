package com.lpenzey.actors

import pdi.jwt.{JwtAlgorithm, JwtClaim, JwtSprayJson}
import akka.actor.{Actor, ActorLogging, Props}
import com.lpenzey.dao.{FavoritesDao, UsersDao}
import com.lpenzey.models.{Favorite, Favorites, User, Users}
import com.lpenzey.helpers.{JsonSupport, TokenService}

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object RegisterUserActor {
  final case object GetUsers
  final case class CreateToken(user: User)
  final case class CreateUser(user: User)
  final case class GetUser(name: String)
  final case class GetFavorites(token: String)
  final case class AddToFavorites(token: String, route: String, stopId: String)
  final case class DeleteUser(token: String)
  final case class ActionPerformed(action: String)
  def props: Props = Props[RegisterUserActor]
}

class RegisterUserActor extends JsonSupport with Actor with ActorLogging with TokenService  {
  import RegisterUserActor._
  import context.dispatcher
  val key: String = System.getenv("JWT_SECRET")

  def receive: Receive = {
    case GetUsers =>
      val getUserSender = sender
      val allUsers: Future[Seq[User]] = UsersDao.getUsers
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

    case DeleteUser(token) =>
      val delSender = sender
      val splitToken = token.split(" ")
      val realToken = splitToken(1)
      val maybeAuthed: Try[JwtClaim] = JwtSprayJson.decode(realToken, key, Seq(JwtAlgorithm.HS256))
      if (maybeAuthed.isSuccess) {
        val userInfo: String = maybeAuthed.get.content
        val numId = getUserId(userInfo)
        val deluser = UsersDao.deleteUser(numId.get)
        deluser.onComplete {
          case Success(delusr) => delSender ! ActionPerformed(s"User was deleted")
          case Failure(unDeletedUser) => delSender ! unDeletedUser
        }
      } else {
        delSender ! ActionPerformed(s"There was an error")
      }

    case CreateToken(user) =>
      val tokenSender = sender

      val token: String = createToken(user)
      val y = JwtSprayJson.decode(token, key, Seq(JwtAlgorithm.HS256))

      tokenSender ! token

    case GetFavorites(token) =>
      val favSender = sender

      val splitToken = token.split(" ")
      val realToken = splitToken(1)
      val isAuthed: Try[JwtClaim] = JwtSprayJson.decode(realToken, key, Seq(JwtAlgorithm.HS256))
      if (isAuthed.isSuccess) {
        val userInfo: String = isAuthed.get.content
        val numId = getUserId(userInfo).get
        val favorites: Future[Seq[Favorite]] = FavoritesDao.getFavorites(numId)
        favorites.andThen {
          case Success(favs: Seq[Favorite]) =>
            favSender ! Favorites(favs)
          case Failure(notFavs: Throwable) =>

            favSender ! notFavs
        }
      } else {
        favSender ! None
      }

    case AddToFavorites(token, route, stopId) =>
      val addToFavSender = sender

      val splitToken = token.split(" ")
      val realToken = splitToken(1)
      val isAuthed: Try[JwtClaim] = JwtSprayJson.decode(realToken, key, Seq(JwtAlgorithm.HS256))
      if (isAuthed.isSuccess) {
          val userInfo: String = isAuthed.get.content
          val numId = getUserId(userInfo)
          val favorites: Future[Int] = FavoritesDao.addFavorite(numId.get, route, stopId)
          favorites.onComplete {
            case Success(favs) =>
              addToFavSender ! favs
            case Failure(notFavs) =>
              addToFavSender ! notFavs
          }
      } else {
          addToFavSender ! None
      }
   }
}