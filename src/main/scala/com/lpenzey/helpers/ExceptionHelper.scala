package com.lpenzey.helpers

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server._
import Directives._

trait ExceptionHelper {

  implicit def myExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case _: ClassCastException =>
        extractUri { uri =>
          println(s"Request to $uri could not be handled normally")
          complete(HttpResponse(InternalServerError, entity = "Bad numbers, bad result!!!"))
        }

    }
}