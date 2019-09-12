package com.lpenzey

import akka.actor.ActorRef
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.lpenzey.actors.RegisterUserActor
import com.lpenzey.routes.UserRoutes
import org.scalatest.concurrent.ScalaFutures
import com.lpenzey.models.{DatabaseSchema, User}
import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}
import org.scalatest.time.{Seconds, Span}

class UserRoutesSpec extends WordSpec
  with Matchers
  with BeforeAndAfter
  with ScalaFutures
  with ScalatestRouteTest
  with UserRoutes
  with DatabaseSchema {
  implicit override val patienceConfig = PatienceConfig(timeout = Span(5, Seconds))

  override val registerUserActor: ActorRef =
  system.actorOf(RegisterUserActor.props, "userRegistry")

  val routes = userRoutes

  "UserRoutes" should {
    "return one user (GET /users/register)" in {
      // note that there's no need for the host part in the uri:
      val request = HttpRequest(uri = "/users/register")

      request ~> routes ~> check {
        status should ===(StatusCodes.OK)

        // we expect the response to be json:
        contentType should ===(ContentTypes.`application/json`)

        // and no entries should be in the list:
        entityAs[String] should ===("""{"users":[{"id":4,"name":"Lucas","password":"myPassword"}]}""")
      }
    }

    "be able to add users (POST /users/register)" in {
      val user = User(None, "Joe", "joespassword")
      val userEntity = Marshal(user).to[MessageEntity].futureValue

      val request = Post("/users/register").withEntity(userEntity)

      request ~> routes ~> check {
        status should ===(StatusCodes.Created)

        contentType should ===(ContentTypes.`application/json`)

        entityAs[String] should ===("""{"action":"User Joe created."}""")
      }
    }

  }
  "be able to find users (GET /users/register)" in {
    val request = HttpRequest(uri = "/users/register/Lucas")

    request ~> routes ~> check {
      status should ===(StatusCodes.OK)

      contentType should ===(ContentTypes.`application/json`)

      entityAs[User].name should ===("Lucas")
    }
  }

}

