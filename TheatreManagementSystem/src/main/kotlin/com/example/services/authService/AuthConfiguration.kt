package com.example.services.authService

import com.example.auth.UserSession
import com.example.db.DatabaseFactory
import com.example.services.authService.repository.AuthRepository
import com.example.services.authService.routes.authRoutes
import com.example.services.authService.service.AuthService
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

    fun Application.configureRouting() {
        authRoutes(AuthService(AuthRepository()))
    }
}

