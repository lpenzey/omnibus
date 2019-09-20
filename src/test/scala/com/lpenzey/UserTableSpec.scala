package com.lpenzey

import com.lpenzey.models.{DatabaseSchema, User}
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.meta._

class UserTableSpec extends FreeSpec with BeforeAndAfter with ScalaFutures with DatabaseSchema {
  implicit override val patienceConfig = PatienceConfig(timeout = Span(5, Seconds))
    var db: Database = _

    def insertUser(name: String, password: String): Int =
      db.run(users += User(Some(1), name, password)).futureValue

    before { db = Database.forConfig("database") }

  "Users Table can" - {
    "create a schema" in {
      val tables = db.run(MTable.getTables).futureValue

      assert(tables.size == 2)
      assert(tables.count(_.name.name.equalsIgnoreCase("users")) == 1)
    }

    after {
      db.close
    }
  }
}
