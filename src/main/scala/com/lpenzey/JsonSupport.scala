
package com.lpenzey

import com.lpenzey.actors.RegisterUserActor.ActionPerformed
import com.lpenzey.models.{User, Users}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonSupport extends SprayJsonSupport {
  import DefaultJsonProtocol._

  implicit val userJsonFormat: RootJsonFormat[User] = jsonFormat3(User)
  implicit val usersJsonFormat: RootJsonFormat[Users] = jsonFormat1(Users)

  implicit val actionPerformedJsonFormat: RootJsonFormat[ActionPerformed] = jsonFormat1(ActionPerformed)
}