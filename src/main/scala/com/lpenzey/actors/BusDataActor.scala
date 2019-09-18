package com.lpenzey.actors

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.http.scaladsl.model.HttpResponse
import com.lpenzey.actors.BusDataActor.HttpWrapper
import com.lpenzey.helpers.JsonSupport
import com.lpenzey.helpers.HttpWrapper

import scala.concurrent.Future
import scala.util.{Failure, Success}

object BusDataActor  {
  final case object GetRoutes
  final case class GetStops(route: String, direction: String)
  final case class GetDirections(route: String)
  final case class GetPredictions(route: String, stopId: String)
  def props: Props = Props[BusDataActor]
  object HttpWrapper extends HttpWrapper {
    override implicit def actorSystem: ActorSystem = ActorSystem("BusDataActor")
  }
}

class BusDataActor(httpWrapper: HttpWrapper = HttpWrapper) extends JsonSupport with Actor with ActorLogging  {
  import BusDataActor._
  import context.dispatcher

  final val BaseUri = "http://www.ctabustracker.com/bustime/api/v2/"
  lazy val Key: String = "?&key=" + System.getenv("BUS_TRACKER_API_KEY")
  final val JsonFormat = "&format=json"
  final val Stops = "getstops"
  final val Routes = "getroutes"
  final val Directions = "getdirections"
  final val Predictions = "getpredictions"
  def getData(url: String): Future[HttpResponse] = httpWrapper.externalService(url)

  def receive: Receive = {
    case GetRoutes =>
      val routeSender = sender
      val routesUrl = BaseUri + Routes + Key + JsonFormat

      val routesFuture: Future[HttpResponse] = getData(routesUrl)

      routesFuture.onComplete {
        case Success(routes) => routeSender ! routes
        case Failure(failedRoutes) =>  println(failedRoutes.toString)
      }

    case GetStops(route, direction) =>
      val stopsSender = sender
      val stopsUri = BaseUri + Stops + Key + "&rt=" + route + "&dir=" + direction + JsonFormat
      val stopsFuture: Future[HttpResponse] = getData(stopsUri)

      stopsFuture.onComplete {
        case Success(routes) => stopsSender ! routes
        case Failure(failedRoutes) =>  println(failedRoutes.toString)
      }

    case GetDirections(route) =>
      val dirSender = sender
      val directionsUri = BaseUri + Directions + Key + "&rt=" + route + JsonFormat
      val directionsFuture: Future[HttpResponse] = getData(directionsUri)

      directionsFuture.onComplete {
        case Success(dirs) => dirSender ! dirs
        case Failure(failedDirs) => dirSender ! failedDirs
      }
    case GetPredictions(route, stopId) =>
      val predictionSender = sender
      val predictionsUri = BaseUri + Predictions + Key + "&rt=" + route + "&stpid=" + stopId + JsonFormat
      val predictionsFuture: Future[HttpResponse] = getData(predictionsUri)

      predictionsFuture.onComplete {
        case Success(stops) => predictionSender ! stops
        case Failure(failedStops) => predictionSender ! failedStops
      }
  }
}