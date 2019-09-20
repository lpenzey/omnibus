package com.lpenzey.dao

import scala.concurrent.ExecutionContext.Implicits.global
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

  def findUserByName(name: String): Future[Option[User]] = usersTable.filter(_.name === name).result.headOption

  def deleteUser(id: Int): Future[Int] = {
    usersTable.filter(_.id === id).delete
  }

  def hashPw(password: String): String = {
    BCrypt.hashpw(password, BCrypt.gensalt(12))
  }

  def authUser(username: String, password: String): Future[Option[User]] = {
      val user: Future[Option[User]] = findUserByName(username)

      user.map {
        user =>
          val maybeUser: User = user.getOrElse(User(None, "anonexistantusername", "$2a$12$ps5VQ3IfieufLOSzS2X6FetSOO/n8ms6UMjnd7Wt9TGJ.jbd74tR2"))
            if (BCrypt.checkpw(password, maybeUser.password)) {
              Some(user).flatten
            } else None
      }
    }
}