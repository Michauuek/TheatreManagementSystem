package com.example.services.reservationService

import com.example.services.reservationService.repository.reservation.ReservationRepository
import com.example.services.reservationService.repository.reservedSeats.ReservedSeatsRepositoryImpl
import com.example.services.reservationService.routes.reservationRoutes
import com.example.services.reservationService.service.reservation.ReservationService
import io.ktor.http.*
import io.ktor.server.application.*
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
        reservationRoutes(ReservationService(ReservationRepository(), ReservedSeatsRepositoryImpl()))
    }
}

