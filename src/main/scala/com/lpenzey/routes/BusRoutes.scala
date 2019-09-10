package com.lpenzey.routes

import akka.actor.{ActorRef, ActorSystem}
import akka.event.Logging
import akka.http.scaladsl.Http

import scala.concurrent.duration._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.{delete, get, post}
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import scala.util.{ Failure, Success }
import scala.concurrent.Future
import akka.util.Timeout
import com.lpenzey.JsonSupport

import scala.util.Success

trait BusRoutes extends JsonSupport {

  implicit def system: ActorSystem

  private val cors = new CORSHandler {}

  def busRoutes: Route = pathPrefix("api") {

    pathPrefix("routes") {
      pathEnd {
        concat(
          get {
            val stopsFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = "http://www.ctabustracker.com/bustime/api/v2/getroutes?key=bvgb6SZYZJydq8SNfpk2gNZba&format=json"))
//
            complete(stopsFuture)
          })
      }
    }
  }

}