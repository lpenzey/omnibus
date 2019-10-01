package com.lpenzey.routes.v1

import akka.actor.{ActorRef, ActorSystem}
import akka.event.Logging

import scala.concurrent.duration._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.headers.RawHeader
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.{delete, get, post}
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete

import scala.concurrent.Future
import com.lpenzey.actors.RegisterUser._
import akka.pattern.ask
import akka.util.Timeout
import com.lpenzey.dao.UsersDao
import com.lpenzey.models.{Favorites, User, Users}
import com.lpenzey.helpers.{AuthenticateBasicAsync, CORSHandler, ExceptionHelper, JsonSupport}

trait UserRoutes
  extends JsonSupport
    with AuthenticateBasicAsync
    with ExceptionHelper
  {
    lazy val usersLog = Logging(system, classOf[UserRoutes])

  implicit def system: ActorSystem
  def registerUser: ActorRef
  implicit lazy val timeout: Timeout = Timeout(10.seconds)
  private val cors = new CORSHandler {}

  def userRoutes: Route = pathPrefix("v1") {
    pathPrefix("users") {
      pathPrefix("register") {
        pathEnd {
          concat(
            get {
              val users: Future[Users] =
                (registerUser ? GetUsers).mapTo[Users]
              cors.corsHandler(complete(users))
            },
            options {
              cors.corsHandler(complete(StatusCodes.OK))
            },
            post {
              entity(as[User]) { user =>
                val userCreated: Future[ActionPerformed] = (registerUser ? CreateUser(user)).mapTo[ActionPerformed]
                onSuccess(userCreated) { performed =>
                  usersLog.info(s"Created User [${user.name}]: ${performed.action}")
                  cors.corsHandler(complete(StatusCodes.Created, performed))
                }
              }
            })
        } ~
          path(Segment) { name =>
            get {
              val maybeUser: Future[User] = (registerUser ? GetUser(name)).mapTo[User]
              rejectEmptyResponse {
                {
                  cors.corsHandler(complete(maybeUser))
                }
              }
            }
          }
      } ~
        delete {
          optionalHeaderValue(extractAuthHeader) { token =>
            val myToken = token.getOrElse("not a token").toString
            val userDeleted: Future[ActionPerformed] = (registerUser ? DeleteUser(myToken)).mapTo[ActionPerformed]
            onSuccess(userDeleted) { performed =>
              usersLog.info(s"Deleted user", performed.action)
              cors.corsHandler(complete(StatusCodes.OK, performed))
            }
          }
        } ~
        pathPrefix("login") {
          pathEnd {
            concat(
              options {
                cors.corsHandler(complete(StatusCodes.OK))
              },
              post {
                authenticateBasicAsync("", UsersDao.authUser) { user =>
                  val token: Future[Any] = registerUser ? CreateToken(user)
                  onSuccess(token) { token =>
                    val authHeader = RawHeader("Authorization", "Bearer " + token.toString)
                    cors.corsHandler(respondWithHeader(authHeader) {
                      complete(StatusCodes.OK)
                    })
                  }
                }
              }
            )
          }
        } ~
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
                    val favorites: Future[Favorites] = (registerUser ? GetFavorites(myToken)).mapTo[Favorites]
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
                      val favorites: Future[Any] = registerUser ? AddToFavorites(myToken, route, stopId)
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