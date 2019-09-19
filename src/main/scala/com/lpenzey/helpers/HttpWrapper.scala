package com.lpenzey.helpers

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import com.lpenzey.config.ActorSystemProvider

import scala.concurrent.Future

trait HttpWrapper extends ActorSystemProvider {
  def externalService(url: String): Future[HttpResponse] =

    Http().singleRequest(HttpRequest(uri = url))

}

