package com.example.services.reservationService

import com.example.db.DatabaseFactory
import com.example.services.reservationService.repository.reservation.ReservationRepositoryImpl
import com.example.services.reservationService.routes.reservationRoutes
import com.example.services.reservationService.service.reservation.ReservationServiceImpl
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*

object ReservationConfiguration {
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
        reservationRoutes(ReservationServiceImpl(ReservationRepositoryImpl()))
    }
}

