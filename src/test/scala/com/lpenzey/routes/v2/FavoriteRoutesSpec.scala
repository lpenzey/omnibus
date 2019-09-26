package com.lpenzey.routes.v2

import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.{BasicHttpCredentials, OAuth2BearerToken}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.{RouteTestTimeout, ScalatestRouteTest}
import com.lpenzey.actors.FavoritesActor
import com.lpenzey.dao.UsersDao
import com.lpenzey.helpers.TokenService
import com.lpenzey.models.{DatabaseSchema, User}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{FreeSpec, Matchers}
import scala.concurrent.duration._

class FavoriteRoutesSpec extends FreeSpec
  with Matchers
  with ScalaFutures
  with ScalatestRouteTest
  with FavoriteRoutes
  with DatabaseSchema
  with TokenService {
  implicit def default(implicit system: ActorSystem): RouteTestTimeout = RouteTestTimeout(5.seconds)

  override val favoritesActor: ActorRef =
    system.actorOf(FavoritesActor.props, "favoritesActor")

  val routes: Route = favoriteRoutes

  "FavoriteRoutes" - {

    "deny access to favorites if not logged in (GET v2/favorites)" in {
      Get(uri = "/v2/favorites")  ~> Route.seal(routes) ~> check {
        status should === (StatusCodes.Unauthorized)
      }
    }

    "allow access to favorites if logged in (GET v2/favorites)" in {
      UsersDao.createUser(User(Some(89), "Reggie", "Reggiespassword")).futureValue
      val reggie: Option[User] = UsersDao.findUserByName("Reggie").futureValue

      val token: String = createToken(reggie.get)

      val reggiesToken = OAuth2BearerToken(token)
      Get(uri = "/v2/favorites") ~> addCredentials(reggiesToken) ~> Route.seal(routes) ~> check {
        status should === (StatusCodes.OK)
      }
    }

    "not allow creating new favorite if no credentials (POST v2/favorites)" in {

      Post(uri = "/v2/favorites?rt=70&stpid=2000") ~> Route.seal(routes) ~> check {
        status should === (StatusCodes.Unauthorized)
      }
    }

    "allow creating new favorite if valid credentials (POST v2/favorites)" in {
      val reggie: Option[User] = UsersDao.findUserByName("Reggie").futureValue

      val token: String = createToken(reggie.get)

      val reggiesToken = OAuth2BearerToken(token)
      Post(uri = "/v2/favorites?rt=70&stpid=2000") ~> addCredentials(reggiesToken) ~> Route.seal(routes) ~> check {
        status should === (StatusCodes.OK)
      }
    }
  }
}

