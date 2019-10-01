package com.lpenzey.helpers

import akka.http.scaladsl.model.HttpHeader
import akka.http.scaladsl.model.headers.{BasicHttpCredentials, HttpChallenges, HttpCredentials, Authorization}
import akka.http.scaladsl.server.AuthenticationFailedRejection.{CredentialsMissing, CredentialsRejected}
import akka.http.scaladsl.server.{AuthenticationFailedRejection, Directive1}
import akka.http.scaladsl.server.Directives._
import scala.concurrent.Future

  trait AuthenticateBasicAsync {
        def authenticateBasicAsync[T](realm: String,
        authenticate: (String, String) => Future[Option[T]]): Directive1[T] = {
            def challenge = HttpChallenges.basic (realm)
              extractCredentials.flatMap {
              case Some (BasicHttpCredentials (username, password) ) =>
              onSuccess (authenticate (username, password) ).flatMap {
              case Some (client) => provide (client)
              case None => reject (AuthenticationFailedRejection (CredentialsRejected, challenge) )
            }
              case _ => reject (AuthenticationFailedRejection (CredentialsMissing, challenge) )
            }
    }

    def extractAuthHeader: HttpHeader => Option[HttpCredentials] = {
      case h: `Authorization` => Some(h.credentials)
      case x         => None
    }
  }