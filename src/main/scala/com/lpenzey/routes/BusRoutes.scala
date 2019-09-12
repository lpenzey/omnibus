package com.lpenzey.routes

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.Http

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.{delete, get, post}
import akka.http.scaladsl.server.directives.RouteDirectives.complete

import scala.concurrent.Future

import com.lpenzey.JsonSupport

trait BusRoutes extends JsonSupport {

    final val BaseUri = "http://www.ctabustracker.com/bustime/api/v2/"
    final val Key = "?&key=bvgb6SZYZJydq8SNfpk2gNZba"
    final val JsonFormat = "&format=json"
    final val Stops = "getstops"
    final val Routes = "getroutes"
    final val Directions = "getdirections"
    final val Predictions = "getpredictions"

  implicit def system: ActorSystem

  private val cors = new CORSHandler {}

  def busRoutes: Route = pathPrefix("api") {

    pathPrefix("routes") {
      pathEnd {
        concat(
          get {
            val stopsFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = BaseUri + Routes + Key + JsonFormat))
            cors.corsHandler(complete(stopsFuture))
          })
        }
    } ~
    pathPrefix("stops") {
      pathEnd {
        concat(
          get {
            parameters('rt.as[String], 'dir.as[String]) { (route, direction) =>
              val stopsUri = BaseUri + Stops + Key + "&rt=" + route + "&dir=" + direction + JsonFormat
              val stopsFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = stopsUri))
              cors.corsHandler(complete(stopsFuture))
            }
          })
      }
    } ~
      pathPrefix("directions") {
        pathEnd {
          concat(
            get {
              parameters('rt.as[String]) { (route) =>
                val stopsUri = BaseUri + Directions + Key + "&rt=" + route + JsonFormat
                val stopsFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = stopsUri))
                cors.corsHandler(complete(stopsFuture))
              }
            })
        }
      } ~
      pathPrefix("predictions") {
        pathEnd {
          concat(
            get {
              parameters('rt.as[String], 'stpid.as[String]) { (route, stopId) =>
                val stopsUri = BaseUri + Predictions + Key + "&rt=" + route + "&stpid=" + stopId + JsonFormat
                val stopsFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = stopsUri))
                cors.corsHandler(complete(stopsFuture))
              }
            })
        }
      }
}
}

