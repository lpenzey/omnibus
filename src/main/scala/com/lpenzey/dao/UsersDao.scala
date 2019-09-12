package com.lpenzey.dao

import com.lpenzey.models.User
import org.mindrot.jbcrypt.BCrypt
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Future

object UsersDao extends BaseDao {
  def getUsers: Future[Seq[User]] = usersTable.result

  def createUser(user: User): Future[Int] = {
    val passHash = BCrypt.hashpw(user.password, BCrypt.gensalt(12))
    val hashedUser: User = User(None, user.name, passHash)
    usersTable.returning(usersTable.map(_.id)) += User(None, user.name, hashPw(user.password))
  }

  def findUserById(userId: Int): Future[User] = usersTable.filter(_.id === userId).result.head

  def findUserByName(name: String): Future[User] = usersTable.filter(_.name === name).result.head

  def deleteUser(name: String): Future[Int] = {
    usersTable.filter(_.name === name).delete
  }

  def hashPw(password: String): String = {
    BCrypt.hashpw(password, BCrypt.gensalt(12))
  }

//  def check(password: String): Boolean = {
//    BCrypt.checkpw(password, "<hashed_password>")
//  }
}