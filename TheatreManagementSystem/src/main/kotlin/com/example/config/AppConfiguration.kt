package com.example.config

import com.example.config.AppConfiguration.configureAuth
import com.example.config.AppConfiguration.configureContentNegotiation
import com.example.data.db.DatabaseFactory
import com.example.di.ServiceProvider
import com.example.exception.ExceptionResponse
import com.example.exception.ParsingException
import com.example.exception.ValidationException
import com.example.routes.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*


import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.contentnegotiation.*

import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.json.Json


object AppConfiguration {
    fun Application.configureDatabase() {
        DatabaseFactory.init()
    }

    fun Application.configureCors() {
        install(CORS){
            //Warning: Do not enable CORS for all routes in a production application. This can lead to security vulnerabilities.
            anyHost()
            allowMethod(HttpMethod.Options)
            allowMethod(HttpMethod.Put)
            allowMethod(HttpMethod.Patch)
            allowMethod(HttpMethod.Delete)
            allowHeader(HttpHeaders.ContentType)
            allowHeader(HttpHeaders.Authorization)
        }
    }

    fun Application.configureContentNegotiation() {
        //TODO

    }

    fun Application.configureRouting() {
        movieRoutes(ServiceProvider.provideSeanceService())
        userRoutes(ServiceProvider.provideUserService())
        hallRoutes(ServiceProvider.provideHallService())
        castRoutes(ServiceProvider.provideCastService())
        actorRoutes(ServiceProvider.provideActorService())
        exceptionRoutes()
    }


    fun Application.configureAuth(){
        val applicationHttpClient = HttpClient(CIO) {
            this@configureAuth.install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }

        install(Authentication) {
            /*basic("admin") {
                //TODO Zrobić to
                realm = "Access to the '/' path"
                validate { credentials ->
                    if (credentials.name == "jetbrains" && credentials.password == "foobar") {
                        UserIdPrincipal(credentials.name)
                    } else {
                        null
                    }
                }
            }
            form("auth-form") {
                // Configure form authentication
            }*/
            oauth("admin") {
                urlProvider = { "http://localhost:8080/callback" }
                providerLookup = {
                    OAuthServerSettings.OAuth2ServerSettings(
                        name = "google",
                        authorizeUrl = "https://accounts.google.com/o/oauth2/auth",
                        accessTokenUrl = "https://accounts.google.com/o/oauth2/token",
                        requestMethod = HttpMethod.Post,
                        clientId = System.getenv("GOOGLE_CLIENT_ID"),
                        clientSecret = System.getenv("GOOGLE_CLIENT_SECRET"),
                        defaultScopes = listOf("https://www.googleapis.com/auth/userinfo.profile"),
                        //extraAuthParameters = listOf("access_type" to "offline")
                    )
                }
                client = applicationHttpClient
            }
        }
    }
}
