package com.example.config

import com.example.data.db.DatabaseFactory
import com.example.di.ServiceProvider
import com.example.routes.hallRoutes
import com.example.routes.movieRoutes
import com.example.routes.scheduleRoutes
import com.example.routes.userRoutes
import io.ktor.serialization.kotlinx.json.*


import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

import io.ktor.server.plugins.cors.routing.*
import kotlinx.serialization.json.Json


object AppConfiguration {

    fun configureDatabase() {
        DatabaseFactory.init()
    }

    fun Application.configureCors() {
        install(CORS){
            anyHost()
        }
    }

    fun Application.configureContentNegotiation() {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }


    fun Application.configureRouting() {
        movieRoutes(ServiceProvider.provideSeanceService())
        userRoutes(ServiceProvider.provideUserService())
        scheduleRoutes(ServiceProvider.provideScheduleService())
        hallRoutes(ServiceProvider.provideHallService())
    }
}
