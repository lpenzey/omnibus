package com.lpenzey.routes.v2

import akka.actor.{ActorRef, ActorSystem}
import akka.event.Logging

import scala.concurrent.duration._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.{get, post}
import akka.http.scaladsl.server.directives.RouteDirectives.complete

import scala.concurrent.Future
import com.lpenzey.actors.FavoritesActor._
import akka.pattern.ask
import akka.util.Timeout
import com.lpenzey.models.Favorites
import com.lpenzey.helpers.{AuthenticateBasicAsync, CORSHandler, ExceptionHelper, JsonSupport}

trait FavoriteRoutes
  extends JsonSupport
    with AuthenticateBasicAsync
    with ExceptionHelper
{
  lazy val favoritesLog = Logging(system, classOf[FavoriteRoutes])

  implicit def system: ActorSystem

  def favoritesActor: ActorRef

  implicit lazy val timeout: Timeout = Timeout(10.seconds)
  private val cors = new CORSHandler {}

  def favoriteRoutes: Route = pathPrefix("v2") {
    pathPrefix("users") {
      pathPrefix("favorites") {
        pathEnd {
          concat(
            options {
              cors.corsHandler(complete(StatusCodes.OK))
            },
            get {
              optionalHeaderValue(extractAuthHeader) { token =>
                val myToken = token.getOrElse("Not found").toString
                if (myToken == "Not found") {
                  cors.corsHandler(complete(StatusCodes.Unauthorized))
                } else {
                  val favorites: Future[Favorites] = (favoritesActor ? GetFavorites(myToken)).mapTo[Favorites]
                  cors.corsHandler(complete(favorites))
                }
              }
            },
            post {
              parameters('rt.as[String], 'stpid.as[String]) { (route, stopId) =>
                optionalHeaderValue(extractAuthHeader) { token =>
                  val myToken = token.getOrElse("Notfound").toString
                  if (myToken == "Notfound") {
                    cors.corsHandler(complete(StatusCodes.Unauthorized))
                  } else {
                    val favorites: Future[Any] = favoritesActor ? AddToFavorites(myToken, route, stopId)
                    onSuccess(favorites) { favorites =>
                      cors.corsHandler(complete(favorites.toString))
                    }
                  }
                }
              }
            }
          )
        }
      }
    }
  }
}
