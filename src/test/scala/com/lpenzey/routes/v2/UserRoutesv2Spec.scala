package com.lpenzey.routes.v2

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.{BasicHttpCredentials, OAuth2BearerToken}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import com.lpenzey.actors.RegisterUser
import com.lpenzey.dao.UsersDao
import com.lpenzey.helpers.TokenService
import com.lpenzey.models.{DatabaseSchema, User}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{FreeSpec, Matchers}

import scala.concurrent.duration._

class UserRoutesv2Spec extends FreeSpec
  with Matchers
  with ScalaFutures
  with ScalatestRouteTest
  with UserRoutesv2
  with DatabaseSchema
  with TokenService {
  implicit def default(implicit system: ActorSystem): RouteTestTimeout = RouteTestTimeout(5.seconds)

  override val registerUser: ActorRef =
    system.actorOf(RegisterUser.props, "registerUser")

  val routes: Route = userRoutesv2

  "UserRoutesv2" - {
    "be able to add users (POST v2/users/register)" in {
      val user = User(Some(42), "Yessica", "Yessicapassword")
      val userEntity = Marshal(user).to[MessageEntity].futureValue

      Post("/v2/users/register").withEntity(userEntity) ~> Route.seal(routes) ~> check {
        status should ===(StatusCodes.Created)

        contentType should ===(ContentTypes.`application/json`)

        entityAs[String] should ===("""{"action":"User Yessica created."}""")
      }
    }

    "Not allow duplicate user names (POST v2/users/register)" in {
      val user = User(Some(42), "Yessica", "Yessicapassword")
      val userEntity = Marshal(user).to[MessageEntity].futureValue

      Post("/v2/users/register").withEntity(userEntity) ~> Route.seal(routes) ~> check {
        status should ===(StatusCodes.Created)

        contentType should ===(ContentTypes.`application/json`)

        entityAs[String] should ===("""{"action":"There was an issue creating this user, try something else."}""")
      }
    }

    "return one user (GET v2/users/register)" in {
      HttpRequest(uri = "/v2/users/register") ~> Route.seal(routes) ~> check {
        status should === (StatusCodes.OK)

        contentType should === (ContentTypes.`application/json`)

        entityAs[String].contains("Reggie") should === (true)
      }
    }

    "be able to find users (GET v2/users/register)" in {
      HttpRequest(uri = "/v2/users/register/Yessica") ~> Route.seal(routes) ~> check {
        status should ===(StatusCodes.OK)

        contentType should ===(ContentTypes.`application/json`)

        entityAs[User].name should ===("Yessica")
      }
    }

    "let a authenticated user login (POST v2/users/login)" in {
      val validCredentials = BasicHttpCredentials("Reggie", "Reggiespassword")

      Post(uri = "/v2/users/login") ~> addCredentials(validCredentials) ~> Route.seal(routes) ~> check {
        assert(response.getHeader("Authorization").isPresent)
        status should ===(StatusCodes.OK)
      }
    }

    "deny access for an incorrect password (POST v2/users/login)" in {
      val invalidCredentials = BasicHttpCredentials("Reggie", "Notreggiespassword")

      Post(uri = "/v2/users/login") ~> addCredentials(invalidCredentials) ~> Route.seal(routes) ~> check {
        status should === (StatusCodes.Unauthorized)
      }
    }

    "deny access for an non-existant user (POST v2/users/login)" in {
      val validCredentials = BasicHttpCredentials("Joseph", "joespassword")

      Post(uri = "/v2/users/login") ~> addCredentials(validCredentials) ~> Route.seal(routes) ~> check {
        status should === (StatusCodes.Unauthorized)
      }
    }

    "not delete a user who has invalid credentials" in {
      val notValidCredentials = BasicHttpCredentials("Reggie", "reggiespssword")

      Delete(uri = "/v2/users/register") ~> addCredentials(notValidCredentials) ~> Route.seal(routes) ~> check {
        entityAs[String] should ===("""{"action":"There was an error"}""")    }
    }

    "delete a user who has valid credentials" in {
      val reggie: Option[User] = UsersDao.findUserByName("Reggie").futureValue

      val token: String = createToken(reggie.get)
      val tokenSplit = token.split(" ")
      val tokenValue = tokenSplit(0)

      val reggiesToken = OAuth2BearerToken(tokenValue)

      Delete(uri = "/v2/users/register") ~> addCredentials(reggiesToken) ~> Route.seal(routes) ~> check {
        entityAs[String] should ===("""{"action":"User was deleted"}""")    }
    }
  }
}

