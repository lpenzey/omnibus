package com.lpenzey.routes

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.BasicHttpCredentials
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import com.lpenzey.actors.RegisterUserActor
import com.lpenzey.models.{DatabaseSchema, User}
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.duration._
import org.scalatest.{FreeSpec, Matchers}

class UserRoutesSpec extends FreeSpec
  with Matchers
  with ScalaFutures
  with ScalatestRouteTest
  with UserRoutes
  with DatabaseSchema {
  implicit def default(implicit system: ActorSystem): RouteTestTimeout = RouteTestTimeout(5.seconds)

  override val registerUserActor: ActorRef =
  system.actorOf(RegisterUserActor.props, "userRegistry")

  val routes: Route = userRoutes

  "UserRoutes" - {
    "return no users when table is empty (GET /users/register)" in {
      val request = HttpRequest(uri = "/users/register")

      request ~> routes ~> check {
        status should === (StatusCodes.OK)

        contentType should === (ContentTypes.`application/json`)

        entityAs[String] should === ("""{"users":[]}""")
      }
    }

    "be able to add users (POST /users/register)" in {
      val user = User(None, "Reggie", "Reggiespassword")
      val userEntity = Marshal(user).to[MessageEntity].futureValue

      val request = Post("/users/register").withEntity(userEntity)

      request ~> routes ~> check {
        status should ===(StatusCodes.Created)

        contentType should ===(ContentTypes.`application/json`)

        entityAs[String] should ===("""{"action":"User Reggie created."}""")
      }
    }

    "return one user (GET /users/register)" in {
      val request = HttpRequest(uri = "/users/register")

      request ~> routes ~> check {
        status should === (StatusCodes.OK)

        contentType should === (ContentTypes.`application/json`)

        entityAs[String].contains("Reggie") should === (true)
      }
    }

    "be able to find users (GET /users/register)" in {
      val request = HttpRequest(uri = "/users/register/Reggie")

      request ~> routes ~> check {
        status should ===(StatusCodes.OK)

        contentType should ===(ContentTypes.`application/json`)

        entityAs[User].name should ===("Reggie")
      }
    }

    "let a authenticated user login (POST /users/login)" in {
      val request = Post(uri = "/users/login")

      val validCredentials = BasicHttpCredentials("Reggie", "Reggiespassword")

      request ~> addCredentials(validCredentials) ~> routes ~> check {
        assert(response.getHeader("Authorization").isPresent)
        status should ===(StatusCodes.OK)
      }
    }

    "deny access for an incorrect password (POST /users/login)" in {
      val request = Post(uri = "/users/login")
      val invalidCredentials = BasicHttpCredentials("Reggie", "Notreggiespassword")


      request ~> addCredentials(invalidCredentials) ~> routes ~> check {
        status should === (StatusCodes.Unauthorized)
      }
    }

    "deny access for an non-existant user (POST /users/login)" in {
      val request = Post(uri = "/users/login")

      val validCredentials = BasicHttpCredentials("Joseph", "joespassword")

      request ~> addCredentials(validCredentials) ~> routes ~> check {
        status should === (StatusCodes.Unauthorized)
      }
    }
  }
}

