package com.lpenzey.models

import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

trait InitialData {
  self: DatabaseSchema =>

  def db: Database

  def insertInitialData(): Future[Unit] = {
    val queries = DBIO.seq(
      favorites.delete, users.delete,
      users += User(Some(1), "devUser", "devPassword"),
      favorites += Favorite(Some(2), 1, 80, 90)
    )
    db.run(queries)
  }
}