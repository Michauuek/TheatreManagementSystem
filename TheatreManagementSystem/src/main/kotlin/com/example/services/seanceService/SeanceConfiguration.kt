package com.example.services.seanceService

import com.example.services.seanceService.service.performance.PerformanceServiceImpl
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
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

object SeanceConfiguration {
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
        movieRoutes(SeanceServiceImpl(SeanceRepositoryImpl()))
        castRoutes(CastServiceImpl(CastRepositoryImpl(), ActorRepositoryImpl()))
        actorRoutes( ActorServiceImpl(ActorRepositoryImpl()))
        performanceRoutes(PerformanceServiceImpl(PerformanceRepositoryImpl(), CastRepositoryImpl()))
    }
}

