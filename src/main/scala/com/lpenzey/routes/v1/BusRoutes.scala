package com.lpenzey.routes.v1

import scala.concurrent.duration._
import akka.actor.{ActorRef, ActorSystem}
import akka.event.{Logging, LoggingAdapter}
import akka.pattern.ask
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.util.Timeout
import com.lpenzey.actors.BusData.{GetDirections, GetPredictions, GetRoutes, GetStops}

import scala.concurrent.Future
import com.lpenzey.helpers.{CORSHandler, JsonSupport}

trait BusRoutes extends JsonSupport {

    final val BaseUri = "http://www.ctabustracker.com/bustime/api/v2/"
    lazy val Key: String = "?&key=" + System.getenv("BUS_TRACKER_API_KEY")
    final val JsonFormat = "&format=json"
    final val Stops = "getstops"
    final val Routes = "getroutes"
    final val Directions = "getdirections"
    final val Predictions = "getpredictions"
    implicit lazy val timeout: Timeout = Timeout(10.seconds)


  implicit def system: ActorSystem
    def busData: ActorRef
    private val cors = new CORSHandler {}

  def busRoutes: Route = pathPrefix("v1") {
    pathPrefix("api") {
      pathPrefix("routes") {
        pathEnd {
          concat(
            get {
              val routesFuture: Future[HttpResponse] = (busData ? GetRoutes).mapTo[HttpResponse]
              cors.corsHandler(complete(routesFuture))
            })
        }
      } ~
        pathPrefix("stops") {
          pathEnd {
            concat(
              get {
                parameters('rt.as[String], 'dir.as[String]) { (route, direction) =>
                  val stopsFuture: Future[HttpResponse] = (busData ? GetStops(route, direction)).mapTo[HttpResponse]
                  cors.corsHandler(complete(stopsFuture))
                }
              })
          }
        } ~
        pathPrefix("directions") {
          pathEnd {
            concat(
              get {
                parameters('rt.as[String]) { route =>
                  val directionsFuture: Future[HttpResponse] = (busData ? GetDirections(route)).mapTo[HttpResponse]
                  cors.corsHandler(complete(directionsFuture))
                }
              })
          }
        } ~
        pathPrefix("predictions") {
          pathEnd {
            concat(
              get {
                parameters('rt.as[String], 'stpid.as[String]) { (route, stopId) =>
                  val predictionsFuture: Future[HttpResponse] = (busData ? GetPredictions(route, stopId)).mapTo[HttpResponse]
                  cors.corsHandler(complete(predictionsFuture))
                }
              })
          }
        }
    }
  }
}
