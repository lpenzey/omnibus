package com.lpenzey.routes

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
import com.lpenzey.actors.RegisterUserActor._
import akka.pattern.ask
import akka.util.Timeout
import com.lpenzey.JsonSupport
import com.lpenzey.models.{User, Users}

trait UserRoutes extends JsonSupport {

  implicit def system: ActorSystem

  lazy val log = Logging(system, classOf[UserRoutes])

  def registerUserActor: ActorRef

  implicit lazy val timeout: Timeout = Timeout(10.seconds)
  val allow = RawHeader("Access-Control-Allow-Origin", "*")
  private val cors = new CORSHandler {}

  def userRoutes: Route = pathPrefix("users") {

      pathPrefix("register") {
        pathEnd {
          concat(
            get {
              val users: Future[Users] =
                (registerUserActor ? GetUsers).mapTo[Users]

              complete(users)
            },
            options {
              cors.corsHandler(complete(StatusCodes.OK))
            },
            post {
              entity(as[User]) { user =>
                val userCreated = (registerUserActor ? CreateUser(user)).mapTo[ActionPerformed]
                onSuccess(userCreated) { performed =>
                  log.info(s"Created User [${user.name}]: ${performed.action}")
                    cors.corsHandler(complete(StatusCodes.Created, performed))
                }
              }
            })
        } ~
          path(Segment) { name =>
            get {
              val maybeUser: Future[User] = (registerUserActor ? GetUser(name)).mapTo[User]
              rejectEmptyResponse {
                respondWithDefaultHeader(allow) {

                  complete(maybeUser)
                }
              }
            } ~
              delete {
                val userDeleted: Future[ActionPerformed] = (registerUserActor ? DeleteUser(name)).mapTo[ActionPerformed]
                onSuccess(userDeleted) { performed =>
                  log.info(s"Deleted user $name", performed.action)
                  complete(StatusCodes.OK, performed)
                }
              }
          }
      }
    }

}