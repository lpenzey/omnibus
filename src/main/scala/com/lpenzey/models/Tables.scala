package com.lpenzey.models

import slick.jdbc.MySQLProfile
import slick.jdbc.MySQLProfile.api._
import slick.lifted.{ForeignKeyQuery, ProvenShape}

case class User(id: Option[Int], name: String, password: String)

case class Favorite(id: Option[Int] = None, userId: Int, route: String, stopId: String)

case class UserWithFavorite(user: User, favorite: Favorite)

case class Favorites(favorites: Seq[Favorite])

case class Users(users: Seq[User])

trait DatabaseSchema {

  class UsersTable(tag: Tag) extends Table[User](tag, "users") {

    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name: Rep[String] = column[String]("name", O.Unique)
    def password: Rep[String] = column[String]("password")
    def * : ProvenShape[User] = (id.?, name, password) <> ((User.apply _).tupled, User.unapply)
  }
  val users = TableQuery[UsersTable]

  class FavoritesTable(tag: Tag) extends Table[Favorite](tag, "favorites") {

    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def userId: Rep[Int] = column[Int]("userId")
    def route: Rep[String] = column[String]("route")
    def stopId: Rep[String] = column[String]("stopId")
    def user: ForeignKeyQuery[UsersTable, User] = foreignKey("USER_FK", userId, users)(_.id, onDelete=ForeignKeyAction.Cascade)
    def * : ProvenShape[Favorite] = (id.?, userId, route, stopId) <> ((Favorite.apply _).tupled, Favorite.unapply)
  }
  val favorites = TableQuery[FavoritesTable]
}


