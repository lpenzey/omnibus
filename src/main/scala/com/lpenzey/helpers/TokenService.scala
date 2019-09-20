package com.lpenzey.helpers

import java.time.Instant

import com.lpenzey.models.User
import pdi.jwt.{JwtAlgorithm, JwtClaim, JwtSprayJson}

trait TokenService  {

  val jwtKey: String = System.getenv("JWT_SECRET")

def getUserId(userInfo: String): Option[Int] = {
  val numPattern = "[0-9]+".r
  val userId: Option[String] = numPattern.findFirstIn(userInfo)

  def toInt(s: String): Option[Int] = {
    try {
      Some(Integer.parseInt(s.trim))
    } catch {
      case e: Exception => None
    }
  }
  toInt(userId.get)
}

def createToken(user: User) = {
  val expiration = Option(Instant.now.plusSeconds(40000).getEpochSecond)
  val issuedAt = Option(Instant.now.getEpochSecond)
  val userName: String = user.name
  val userId: String = user.id.toString
  val claim = JwtClaim(s"""{"name":"$userName","id":"$userId"}""", None, None, None, expiration, None, issuedAt)
  val algo = JwtAlgorithm.HS256
JwtSprayJson.encode(claim, jwtKey, algo)

}

}