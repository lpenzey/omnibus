package com.lpenzey.dao

import com.lpenzey.models.User

import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

object UsersDao extends BaseDao {

  def getUsers: Future[Seq[User]] = usersTable.result
  def createUser(user: User): Future[Int] = usersTable.returning(usersTable.map(_.id)) += user
  def findUserById(userId: Int): Future[User] = usersTable.filter(_.id === userId).result.head
  def deleteUser(userId: Int): Future[Int] = usersTable.filter(_.id === userId).delete
}