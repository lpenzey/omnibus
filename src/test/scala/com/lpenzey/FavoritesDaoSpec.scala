package com.lpenzey

import com.lpenzey.dao.FavoritesDao
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures

class FavoritesDaoSpec extends FreeSpec with ScalaFutures {

  "FavoritesDao can" - {
    "Returns no favorites if none are present" in {
      val maybeFavorites = FavoritesDao.getFavorites(42).futureValue
      assert(maybeFavorites.headOption === None)
    }
  }
}
