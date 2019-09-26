package com.lpenzey.helpers

import com.lpenzey.actors.RegisterUser.ActionPerformed
import com.lpenzey.models.{Favorite, Favorites, User, Users}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonSupport extends SprayJsonSupport {
  import DefaultJsonProtocol._

  implicit val userJsonFormat: RootJsonFormat[User] = jsonFormat3(User)
  implicit val usersJsonFormat: RootJsonFormat[Users] = jsonFormat1(Users)
  implicit val favoriteJsonFormat: RootJsonFormat[Favorite] = jsonFormat4(Favorite)
  implicit val favoritesJsonFormat: RootJsonFormat[Favorites] = jsonFormat1(Favorites)
  implicit val actionPerformedJsonFormat: RootJsonFormat[ActionPerformed] = jsonFormat1(ActionPerformed)
}