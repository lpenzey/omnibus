package com.lpenzey.dao

import com.lpenzey.models.Favorite
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

object FavoritesDao extends BaseDao {

  def getFavorites(userId: Int): Future[Seq[Favorite]] = {
    val compiledFavorites = Compiled { userId: Rep[Int] => favoritesTable.filter(_.userId === userId) }

    val getFavoritesQuery =  compiledFavorites.extract
    getFavoritesQuery(userId).result
  }
  def addFavorite(userId: Int, route: String, stopId: String): Future[Int] = {

    favoritesTable.returning(favoritesTable.map(_.id)) += Favorite(None, userId, route, stopId)
  }

}