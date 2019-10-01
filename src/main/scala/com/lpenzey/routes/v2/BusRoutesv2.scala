package com.lpenzey.routes.v2

import scala.concurrent.duration._
import akka.pattern.ask
import akka.http.scaladsl.server.Directives._
import akka.actor.{ActorRef, ActorSystem}
import akka.event.Logging
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.Directives.{concat, parameters, pathEnd, pathPrefix}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.util.Timeout
import com.lpenzey.actors.BusData.{GetDirections, GetPredictions, GetRoutes, GetStops}
import com.lpenzey.helpers.{CORSHandler, JsonSupport}

import scala.concurrent.Future

trait BusRoutesv2 extends JsonSupport {

  implicit lazy val timeout: Timeout = Timeout(10.seconds)

  implicit def system: ActorSystem
  def busData: ActorRef
  private val cors = new CORSHandler {}


  def busRoutesv2: Route = pathPrefix("v2") {
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
