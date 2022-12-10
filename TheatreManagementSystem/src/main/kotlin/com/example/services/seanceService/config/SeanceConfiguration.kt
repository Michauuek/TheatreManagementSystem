package com.example.services.seanceService.config

import com.example.auth.UserSession
import com.example.db.DatabaseFactory
import com.example.reservationService.service.hall.performance.PerformanceServiceImpl
import com.example.services.seanceService.repository.actor.ActorRepositoryImpl
import com.example.services.seanceService.repository.cast.CastRepositoryImpl
import com.example.services.seanceService.repository.performance.PerformanceRepositoryImpl
import com.example.services.seanceService.repository.seance.SeanceRepositoryImpl
import com.example.services.seanceService.routes.actorRoutes
import com.example.services.seanceService.routes.castRoutes
import com.example.services.seanceService.routes.movieRoutes
import com.example.services.seanceService.routes.performanceRoutes
import com.example.services.seanceService.service.actor.ActorServiceImpl
import com.example.services.seanceService.service.cast.CastServiceImpl
import com.example.services.seanceService.service.seance.SeanceServiceImpl
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

object SeanceConfiguration {
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
        movieRoutes(SeanceServiceImpl(SeanceRepositoryImpl()))
        castRoutes(CastServiceImpl(CastRepositoryImpl(), ActorRepositoryImpl()))
        actorRoutes( ActorServiceImpl(ActorRepositoryImpl()))
        performanceRoutes(PerformanceServiceImpl(PerformanceRepositoryImpl()))
//        authRoutes()
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
    fun Application.configureSession(){
        install(Sessions){
            cookie<UserSession>("user_session") {
                cookie.path = "/"
                cookie.extensions["SameSite"] = "lax"
            }
        }
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

        configureSession()

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

