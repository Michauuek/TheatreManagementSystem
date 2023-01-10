package com.example.services.seanceService

import com.example.services.seanceService.service.performance.PerformanceService
import com.example.services.seanceService.repository.actor.ActorRepository
import com.example.services.seanceService.repository.cast.CastRepository
import com.example.services.seanceService.repository.performance.PerformanceRepository
import com.example.services.seanceService.repository.seance.SeanceRepository
import com.example.services.seanceService.routes.actorRoutes
import com.example.services.seanceService.routes.castRoutes
import com.example.services.seanceService.routes.movieRoutes
import com.example.services.seanceService.routes.performanceRoutes
import com.example.services.seanceService.service.actor.ActorService
import com.example.services.seanceService.service.cast.CastService
import com.example.services.seanceService.service.seance.SeanceService
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
        movieRoutes(SeanceService(SeanceRepository()))
        castRoutes(CastService(CastRepository(), ActorRepository()))
        actorRoutes( ActorService(ActorRepository()))
        performanceRoutes(PerformanceService(PerformanceRepository(), CastRepository()))
    }
}

