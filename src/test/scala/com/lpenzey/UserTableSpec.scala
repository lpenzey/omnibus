package com.lpenzey

import com.lpenzey.models.{DatabaseSchema, User}
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.meta._

class UserTableSpec extends FunSuite with BeforeAndAfter with ScalaFutures with DatabaseSchema {
  implicit override val patienceConfig = PatienceConfig(timeout = Span(5, Seconds))
    var db: Database = _

    def createSchema() =
      db.run((users.schema).create).futureValue

    def insertUser(): Int =
      db.run(users += User(Some(1), "lucas", "lucasPassword")).futureValue

    before { db = Database.forConfig("h2mem1") }

  test("Can create a schema") {
    createSchema()

    val tables = db.run(MTable.getTables).futureValue

    assert(tables.size == 1)
    assert(tables.count(_.name.name.equalsIgnoreCase("users")) == 1)
  }

  test("Can insert a user") {
    createSchema()

    val insertCount = insertUser()
    assert(insertCount == 1)
  }

  test("Can query for a user") {
    createSchema()
    insertUser()
    val results = db.run(users.result).futureValue
    assert(results.size == 1)
    assert(results.head.name == "lucas")
  }

  after { db.close }
}
