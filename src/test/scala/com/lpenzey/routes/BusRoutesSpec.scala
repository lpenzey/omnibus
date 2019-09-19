package com.lpenzey.routes

import akka.actor.{ActorRef, Props}
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.lpenzey.actors.BusDataActor
import com.lpenzey.helpers.HttpWrapper
import org.scalatest.{FreeSpec, Matchers}
import org.scalamock.scalatest.MockFactory

import scala.concurrent.Future

class BusRoutesSpec extends FreeSpec
  with Matchers
  with ScalatestRouteTest
  with BusRoutes
  with MockFactory {

  val routesFuture: Future[HttpResponse] = Marshal(JSONFixtures.routes).to[HttpResponse]
  val directionsFuture: Future[HttpResponse] = Marshal(JSONFixtures.directions).to[HttpResponse]
  val stopsFuture: Future[HttpResponse] = Marshal(JSONFixtures.stops).to[HttpResponse]
  val predictionsFuture: Future[HttpResponse] = Marshal(JSONFixtures.predctions).to[HttpResponse]

  val stubHttp: HttpWrapper = stub[HttpWrapper]




  override val busDataActor: ActorRef =
    system.actorOf(Props(new BusDataActor(stubHttp)), "busDataActor")

  val routes = busRoutes
    "BusRoutes" - {
      "return all available routes (GET /api/routes)" in {
        stubHttp.externalService _ when JSONFixtures.routesUri returns routesFuture
        val routesRequest = HttpRequest(uri = "/api/routes")

        routesRequest ~> routes ~> check {
        status should ===(StatusCodes.OK)
        entityAs[String] should === (JSONFixtures.routes)
      }
    }
      "return all directions for a given route (GET /api/directions)" in {
        stubHttp.externalService _ when JSONFixtures.directionsUri returns directionsFuture
        val directionsRequest = HttpRequest(uri = "/api/directions?rt=70")

        directionsRequest ~> routes ~> check {
          status should ===(StatusCodes.OK)
          entityAs[String] should === (JSONFixtures.directions)
        }
      }
      "return all stops for a given route and direction (GET /api/stops)" in {
      stubHttp.externalService _ when JSONFixtures.stopsUri returns stopsFuture
        val stopsRequest = HttpRequest(uri = "/api/stops?rt=70&dir=Eastbound")

        stopsRequest ~> routes ~> check {
          status should ===(StatusCodes.OK)
          entityAs[String] should === (JSONFixtures.stops)
        }
      }
      "return all predictions for a given route and stopId (GET /api/predictions)" in {
        stubHttp.externalService _ when JSONFixtures.predictionsUri returns predictionsFuture
        val stopsRequest = HttpRequest(uri = "/api/predictions?rt=70&stpid=2000")

        stopsRequest ~> routes ~> check {
          status should ===(StatusCodes.OK)
          entityAs[String] should === (JSONFixtures.predctions)
        }
      }
  }
}