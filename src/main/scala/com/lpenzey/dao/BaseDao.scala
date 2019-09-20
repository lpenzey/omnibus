package com.lpenzey.dao

import com.lpenzey.config.DatabaseConfig
import com.lpenzey.models.DatabaseSchema
import slick.dbio.NoStream
import slick.lifted.TableQuery
import slick.sql.{FixedSqlStreamingAction, SqlAction}

import scala.concurrent.Future

trait BaseDao extends DatabaseSchema {

  val usersTable = TableQuery[UsersTable]
  val favoritesTable = TableQuery[FavoritesTable]

  protected implicit def executeFromDb[A](action: SqlAction[A, NoStream, _ <: slick.dbio.Effect]): Future[A] = {
    DatabaseConfig.db.run(action)
  }

  protected implicit def executeReadStreamFromDb[A](action: FixedSqlStreamingAction[Seq[A], A, _ <: slick.dbio.Effect]): Future[Seq[A]] = {
    DatabaseConfig.db.run(action)
  }
}