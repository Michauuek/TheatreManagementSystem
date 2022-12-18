package com.example.config

import com.example.auth.UserSession
import com.example.db.DatabaseFactory
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*


import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.contentnegotiation.*

import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.*
import kotlinx.serialization.json.Json

fun Application.configureDatabase() {
    DatabaseFactory.init()
}

fun Application.configureBasicCors() {
    install(CORS) {
        //Warning: Do not enable CORS for all routes in a production application. This can lead to security vulnerabilities.
        //TODO Turn off portion of CORS for production

        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Patch)
        allowMethod(HttpMethod.Delete)

        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.Origin)

        allowCredentials = true // allow cookies
        allowSameOrigin = true

        allowHeader("user_session")
        exposeHeader("user_session")

        anyHost()
    }
}

var applicationHttpClient: HttpClient? = null;

fun Application.getHttpClient(): HttpClient {
    if (applicationHttpClient == null) {
        throw Exception("HttpClient not initialized")
    }
    return applicationHttpClient!!
}

fun Application.configureAuth() {
    applicationHttpClient = HttpClient(CIO) {
        this@configureAuth.install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    install(Sessions) {
        val secretEncryptKey = hex("000102030405060708090a0b0c0d0e0f")
        val secretSignKey = hex("a70fca0d33b197f8985004400a81d83b219c30ab3ee29db3a4c7867ff7de2f2d")
        header<UserSession>("user_session") {
            transform(SessionTransportTransformerEncrypt(secretEncryptKey, secretSignKey))
        }
    }

    install(Authentication) {
        oauth("admin") {
            urlProvider = { "http://localhost:8084/seance/auth/callback" }
            providerLookup = {
                OAuthServerSettings.OAuth2ServerSettings(
                    name = "google",
                    authorizeUrl = "https://accounts.google.com/o/oauth2/auth",
                    accessTokenUrl = "https://oauth2.googleapis.com/token",
                    requestMethod = HttpMethod.Post,
                    clientId = System.getenv("CLIENT_ID"),
                    clientSecret = System.getenv("CLIENT_SECRET"),
                    defaultScopes = listOf("https://www.googleapis.com/auth/userinfo.profile"),
                )
            }
            client = applicationHttpClient!!
        }
    }
}

