package com.example.services.hallService

import com.example.db.DatabaseFactory
import com.example.routes.reservationService.repository.hall.HallRepositoryImpl
import com.example.services.hallService.routes.hallRoutes
import com.example.routes.reservationService.service.hall.HallServiceImpl
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*

object HallConfiguration {
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
        hallRoutes(HallServiceImpl(HallRepositoryImpl()))
    }
}

