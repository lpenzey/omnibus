package com.lpenzey.models

import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape

case class User(id: Option[Int], name: String, password: String)

case class Favorite(id: Option[Int] = None, userId: Int, routeId: Int, stopId: Int)

case class UserWithFavorite(user: User, favorite: Favorite)

case class Users(users: Seq[User])

trait DatabaseSchema {

  class UsersTable(tag: Tag) extends Table[User](tag, "users") {

    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def name: Rep[String] = column[String]("name")

    def password: Rep[String] = column[String]("password")

    def * : ProvenShape[User] = (id.?, name, password) <> ((User.apply _).tupled, User.unapply)
  }
  val users = TableQuery[UsersTable]
  class FavoritesTable(tag: Tag) extends Table[Favorite](tag, "favorites") {

    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def userId: Rep[Int] = column[Int]("userId")

    def routeId: Rep[Int] = column[Int]("routeId")

    def stopId: Rep[Int] = column[Int]("stopId")

    def user = foreignKey("FK_USER", userId, users)(_.id)

    def * : ProvenShape[Favorite] = (id.?, userId, routeId, stopId) <> ((Favorite.apply _).tupled, Favorite.unapply)
  }
  val favorites = TableQuery[FavoritesTable]
}


