package com.lpenzey.helpers

import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.headers._
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.http.scaladsl.server.{Directive0, Route}

import scala.concurrent.duration._

trait CORSHandler {

  private val corsResponseHeaders = List(
    `Access-Control-Allow-Origin`("http://localhost:3000"),
    `Access-Control-Allow-Credentials`(true),
    `Access-Control-Expose-Headers`("Authorization"),
    `Access-Control-Allow-Headers`("Authorization",
      "Content-Type", "X-Requested-With", "Set-Cookie"),
    `Access-Control-Max-Age`(1.day.toMillis)
  )

  private def addAccessControlHeaders: Directive0 = {
    respondWithHeaders(corsResponseHeaders)
  }

  private def preflightRequestHandler: Route = options {
    complete(HttpResponse(StatusCodes.OK).
      withHeaders(`Access-Control-Allow-Methods`(OPTIONS, POST, GET, DELETE)))
  }

  def corsHandler(r: Route): Route = addAccessControlHeaders {
    preflightRequestHandler ~ r
  }

  def addCORSHeaders(response: HttpResponse):HttpResponse =
    response.withHeaders(corsResponseHeaders)
}