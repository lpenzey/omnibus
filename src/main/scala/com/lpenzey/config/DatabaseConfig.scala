package com.lpenzey.config

object DatabaseConfig  {

  val driver = slick.jdbc.MySQLProfile

  import driver.api._

  def db: driver.backend.DatabaseDef = Database.forConfig("database")

  implicit val session: Session = db.createSession()
}