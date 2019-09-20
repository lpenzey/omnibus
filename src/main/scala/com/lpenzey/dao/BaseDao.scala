package com.lpenzey.dao

import com.lpenzey.models.DatabaseSchema
import slick.dbio.NoStream
import slick.lifted.TableQuery
import slick.sql.{FixedSqlStreamingAction, SqlAction}

import scala.concurrent.Future



trait BaseDao extends DatabaseSchema {
  object BaseDao  {

    val driver = slick.jdbc.MySQLProfile

    import driver.api._

    def db: driver.backend.DatabaseDef = Database.forConfig("database")

    implicit val session: Session = db.createSession()
  }
  val usersTable = TableQuery[UsersTable]
  val favoritesTable = TableQuery[FavoritesTable]

  protected implicit def executeFromDb[A](action: SqlAction[A, NoStream, _ <: slick.dbio.Effect]): Future[A] = {
    BaseDao.db.run(action)
  }

  protected implicit def executeReadStreamFromDb[A](action: FixedSqlStreamingAction[Seq[A], A, _ <: slick.dbio.Effect]): Future[Seq[A]] = {
    BaseDao.db.run(action)
  }
}