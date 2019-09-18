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

  stubHttp.externalService _ when JSONFixtures.routesUri returns routesFuture
  stubHttp.externalService _ when JSONFixtures.routesUri returns directionsFuture
  stubHttp.externalService _ when JSONFixtures.routesUri returns stopsFuture
  stubHttp.externalService _ when JSONFixtures.routesUri returns predictionsFuture


  override val busDataActor: ActorRef =
    system.actorOf(Props(new BusDataActor(stubHttp)), "busDataActor")

  val routes = busRoutes
  "BusRoutes" - {
    "return all available routes (GET /api/routes)" in {
      val request = HttpRequest(uri = "/api/routes")

      request ~> routes ~> check {
        status should ===(StatusCodes.OK)
        entityAs[String] should === (JSONFixtures.routes)
//      }
    }
  }
}}