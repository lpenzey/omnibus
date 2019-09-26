package com.lpenzey.routes.v1

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

class UserRoutesSpec extends FreeSpec
  with Matchers
  with ScalaFutures
  with ScalatestRouteTest
  with UserRoutes
  with DatabaseSchema
  with TokenService {
  implicit def default(implicit system: ActorSystem): RouteTestTimeout = RouteTestTimeout(5.seconds)

  override val registerUser: ActorRef =
  system.actorOf(RegisterUser.props, "registerUser")

  val routes: Route = userRoutes

  "UserRoutes" - {
    "return no users when table is empty (GET v1/users/register)" in {
      HttpRequest(uri = "/v1/users/register") ~> Route.seal(routes) ~> check {
        status should === (StatusCodes.OK)

        contentType should === (ContentTypes.`application/json`)

        entityAs[String] should === ("""{"users":[]}""")
      }
    }

    "be able to add users (POST v1/users/register)" in {
      val user = User(Some(42), "Reggie", "Reggiespassword")
      val userEntity = Marshal(user).to[MessageEntity].futureValue

      Post("/v1/users/register").withEntity(userEntity) ~> Route.seal(routes) ~> check {
        status should ===(StatusCodes.Created)

        contentType should ===(ContentTypes.`application/json`)

        entityAs[String] should ===("""{"action":"User Reggie created."}""")
      }
    }

    "return one user (GET v1/users/register)" in {
      HttpRequest(uri = "/v1/users/register") ~> Route.seal(routes) ~> check {
        status should === (StatusCodes.OK)

        contentType should === (ContentTypes.`application/json`)

        entityAs[String].contains("Reggie") should === (true)
      }
    }

    "be able to find users (GET v1/users/register/UserName)" in {
      HttpRequest(uri = "/v1/users/register/Reggie") ~> Route.seal(routes) ~> check {
        status should ===(StatusCodes.OK)

        contentType should ===(ContentTypes.`application/json`)

        entityAs[User].name should ===("Reggie")
      }
    }

    "let a authenticated user login (POST v1/users/login)" in {
      val validCredentials = BasicHttpCredentials("Reggie", "Reggiespassword")

      Post(uri = "/v1/users/login") ~> addCredentials(validCredentials) ~> Route.seal(routes) ~> check {
        assert(response.getHeader("Authorization").isPresent)
        status should ===(StatusCodes.OK)
      }
    }

    "deny access for an incorrect password (POST v1/users/login)" in {
      val invalidCredentials = BasicHttpCredentials("Reggie", "Notreggiespassword")

      Post(uri = "/v1/users/login") ~> addCredentials(invalidCredentials) ~> Route.seal(routes) ~> check {
        status should === (StatusCodes.Unauthorized)
      }
    }

    "deny access for an non-existant user (POST v1/users/login)" in {
      val validCredentials = BasicHttpCredentials("Joseph", "joespassword")

      Post(uri = "/v1/users/login") ~> addCredentials(validCredentials) ~> Route.seal(routes) ~> check {
        status should === (StatusCodes.Unauthorized)
      }
    }

    "deny access to favorites if not logged in (GET v1/users/favorites)" in {
      Get(uri = "/v1/users/favorites")  ~> Route.seal(routes) ~> check {
        status should === (StatusCodes.Unauthorized)
      }
    }

    "allow access to favorites if logged in (GET v1/users/favorites)" in {
      UsersDao.createUser(User(Some(89), "Reggie", "Reggiespassword")).futureValue
      val reggie: Option[User] = UsersDao.findUserByName("Reggie").futureValue

      val token: String = createToken(reggie.get)

      val reggiesToken = OAuth2BearerToken(token)
      Get(uri = "/v1/users/favorites") ~> addCredentials(reggiesToken) ~> Route.seal(routes) ~> check {
        status should === (StatusCodes.OK)
      }
    }

    "not allow creating new favorite if no credentials (POST v1/users/favorites)" in {

      Post(uri = "/v1/users/favorites?rt=70&stpid=2000") ~> Route.seal(routes) ~> check {
        status should === (StatusCodes.Unauthorized)
      }
    }

    "allow creating new favorite if valid credentials (POST v1/users/favorites)" in {
      val reggie: Option[User] = UsersDao.findUserByName("Reggie").futureValue

      val token: String = createToken(reggie.get)

      val reggiesToken = OAuth2BearerToken(token)
      Post(uri = "/v1/users/favorites?rt=70&stpid=2000") ~> addCredentials(reggiesToken) ~> Route.seal(routes) ~> check {
        status should === (StatusCodes.OK)
      }
    }

    "not delete a user who has invalid credentials" in {
      val notValidCredentials = BasicHttpCredentials("Reggie", "reggiespssword")

      Delete(uri = "/v1/users/register") ~> addCredentials(notValidCredentials) ~> Route.seal(routes) ~> check {
        entityAs[String] should ===("""{"action":"There was an error"}""")    }
    }

    "delete a user who has valid credentials" in {
      val reggie: Option[User] = UsersDao.findUserByName("Reggie").futureValue

      val token: String = createToken(reggie.get)
      val tokenSplit = token.split(" ")
      val tokenValue = tokenSplit(0)

      val reggiesToken = OAuth2BearerToken(tokenValue)

      Delete(uri = "/v1/users/register") ~> addCredentials(reggiesToken) ~> Route.seal(routes) ~> check {
        entityAs[String] should ===("""{"action":"User was deleted"}""")    }
    }
  }
}

