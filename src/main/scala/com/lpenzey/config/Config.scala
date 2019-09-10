package com.lpenzey.config

import com.typesafe.config.ConfigFactory

trait Config {
  private val config = ConfigFactory.load()
  private val databaseConfig = config.getConfig("mysql")
  println(databaseConfig)

  val databaseUrl: String = databaseConfig.getString("url")
  val databaseUser: String = databaseConfig.getString("user")
  val databasePassword: String = databaseConfig.getString("password")
}