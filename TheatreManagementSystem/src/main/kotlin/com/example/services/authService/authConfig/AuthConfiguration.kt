package com.example.services.authService.authConfig

import com.example.auth.UserSession
import com.example.db.DatabaseFactory
import com.example.routes.authRoutes
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*


import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.contentnegotiation.*

import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.sessions.*
import kotlinx.serialization.json.Json

object AuthConfiguration {
    fun Application.configureDatabase() {
        DatabaseFactory.init()
    }
    fun Application.configureCors() {
        install(CORS){
            //Warning: Do not enable CORS for all routes in a production application. This can lead to security vulnerabilities.

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

            anyHost()
        }
    }
    fun Application.configureContentNegotiation() {
        install(ContentNegotiation) {
            json()
        }
    }

    fun Application.configureRouting() {
        authRoutes()
    }
    var applicationHttpClient: HttpClient? = null;

    fun Application.getHttpClient(): HttpClient {
        if (applicationHttpClient == null) {
            applicationHttpClient = HttpClient(CIO) {
                expectSuccess = false
            }
        }
        return applicationHttpClient!!
    }
    fun Application.configureAuth(){
        applicationHttpClient = HttpClient(CIO) {
            this@configureAuth.install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }

        install(Sessions){
            cookie<UserSession>("user_session") {
                cookie.path = "/"
                cookie.extensions["SameSite"] = "lax"
            }
        }

        install(Authentication) {
        oauth("admin") {
            urlProvider = { "http://localhost:8080/seance/auth/callback" }
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
}

