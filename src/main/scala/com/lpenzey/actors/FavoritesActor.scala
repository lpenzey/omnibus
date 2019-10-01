package com.lpenzey.actors

import pdi.jwt.{JwtAlgorithm, JwtClaim, JwtSprayJson}
import akka.actor.{Actor, ActorLogging, Props}
import com.lpenzey.dao.FavoritesDao
import com.lpenzey.models.{Favorite, Favorites}
import com.lpenzey.helpers.{JsonSupport, TokenService}

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object FavoritesActor {
  final case class GetFavorites(token: String)
  final case class AddToFavorites(token: String, route: String, stopId: String)
  final case class ActionPerformed(action: String)
  def props: Props = Props[FavoritesActor]
}

class FavoritesActor extends JsonSupport with Actor with ActorLogging with TokenService  {
  import FavoritesActor._
  import context.dispatcher
  val key: String = System.getenv("JWT_SECRET")

  def receive: Receive = {
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
        favSender ! akka.actor.Status.Failure(new Throwable("An error occurred"))
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
        addToFavSender ! akka.actor.Status.Failure(new Throwable("An error occurred"))
      }
  }
}