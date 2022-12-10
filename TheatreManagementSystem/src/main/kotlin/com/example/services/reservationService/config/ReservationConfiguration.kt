package com.example.services.reservationService.config

import com.example.auth.UserSession
import com.example.db.DatabaseFactory
import com.example.reservationService.service.hall.performance.PerformanceServiceImpl
import com.example.services.reservationService.repository.reservation.ReservationRepositoryImpl
import com.example.services.reservationService.routes.reservationRoutes
import com.example.services.reservationService.service.reservation.ReservationServiceImpl
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
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.sessions.*

object ReservationConfiguration {
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
        reservationRoutes(ReservationServiceImpl(ReservationRepositoryImpl()))
    }
}

