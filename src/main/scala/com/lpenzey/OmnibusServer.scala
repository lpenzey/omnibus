package com.lpenzey
import akka.http.scaladsl.server.Directives._

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}
import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.lpenzey.actors.BusDataActor.HttpWrapper
import com.lpenzey.actors.{BusDataActor, RegisterUserActor}
import com.lpenzey.routes.{BusRoutes, UserRoutes}

object OmnibusServer extends App with UserRoutes with BusRoutes {

  implicit val system: ActorSystem = ActorSystem("omnibusServer")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  val registerUserActor: ActorRef = system.actorOf(RegisterUserActor.props, "registerUserActor")
  val busDataActor: ActorRef = system.actorOf(Props(new BusDataActor(HttpWrapper)), "busDataActor")

  lazy val routes: Route = userRoutes ~ busRoutes
  val port: Int = sys.env.getOrElse("PORT", "8080").toInt
  val serverBinding: Future[Http.ServerBinding] = Http().bindAndHandle(routes, "0.0.0.0", port)
  override implicit lazy val timeout: Timeout = Timeout(10.seconds)

  serverBinding.onComplete {
    case Success(bound) =>
      println(s"Server online at http://${bound.localAddress.getHostString}:${bound.localAddress.getPort}/")
    case Failure(e) =>
      Console.err.println(s"Server could not start!")
      e.printStackTrace()
      system.terminate()
  }

  Await.result(system.whenTerminated, Duration.Inf)
}