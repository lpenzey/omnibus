package com.lpenzey.dao

import com.lpenzey.config.DatabaseConfig
import com.lpenzey.models.DatabaseSchema
import slick.dbio.NoStream
import slick.lifted.TableQuery
import slick.sql.{FixedSqlStreamingAction, SqlAction}

import scala.concurrent.Future

trait BaseDao extends DatabaseConfig with DatabaseSchema {
  val usersTable = TableQuery[UsersTable]
  val favoritesTable = TableQuery[FavoritesTable]

  protected implicit def executeFromDb[A](action: SqlAction[A, NoStream, _ <: slick.dbio.Effect]): Future[A] = {
    db.run(action)
  }

  protected implicit def executeReadStreamFromDb[A](action: FixedSqlStreamingAction[Seq[A], A, _ <: slick.dbio.Effect]): Future[Seq[A]] = {
    db.run(action)
  }
}