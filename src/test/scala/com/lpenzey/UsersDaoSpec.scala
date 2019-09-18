package com.lpenzey

import com.lpenzey.dao.UsersDao
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures

class UsersDaoSpec extends FreeSpec with ScalaFutures {

  "UsesDao can" - {
    "Handle searching for a user that doesn't exist" in {
      val maybeUser = UsersDao.findUserByName("NotAUser").futureValue

      assert(maybeUser === None)
    }
  }
}
