package com.lpenzey.routes.v2

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
import com.lpenzey.models.{User, Users}
import com.lpenzey.helpers.{AuthenticateBasicAsync, CORSHandler, ExceptionHelper, JsonSupport}

trait UserRoutesv2
  extends JsonSupport
    with AuthenticateBasicAsync
    with ExceptionHelper
{
  lazy val usersV2Log = Logging(system, classOf[UserRoutesv2])

  implicit def system: ActorSystem

  def registerUser: ActorRef

  implicit lazy val timeout: Timeout = Timeout(10.seconds)

  private val cors = new CORSHandler {}

  def userRoutesv2: Route = pathPrefix("v2") {
    pathPrefix("users") {
      pathPrefix("register") {
        pathEnd {
          concat(
            options {
              cors.corsHandler(complete(StatusCodes.OK))
            },
            post {
              entity(as[User]) { user =>
                val userCreated: Future[ActionPerformed] = (registerUser ? CreateUser(user)).mapTo[ActionPerformed]
                onSuccess(userCreated) { performed =>
                  usersV2Log.info(s"${performed.action}")
                  cors.corsHandler(complete(StatusCodes.Created, performed))
                }
              }
            }
          )}
      } ~
        delete {
          optionalHeaderValue(extractAuthHeader) { token =>
            val myToken = token.getOrElse("not a token").toString
            val userDeleted: Future[ActionPerformed] = (registerUser ? DeleteUser(myToken)).mapTo[ActionPerformed]
            onSuccess(userDeleted) { performed =>
              usersV2Log.info(s"Deleted user", performed.action)
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
        }
    }
  }
}
