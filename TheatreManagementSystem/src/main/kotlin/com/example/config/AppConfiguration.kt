package com.example.config

import com.example.data.db.DatabaseFactory
import com.example.di.ServiceProvider
import com.example.exception.ExceptionResponse
import com.example.exception.ParsingException
import com.example.exception.ValidationException
import com.example.routes.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*


import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
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
        hallRoutes(ServiceProvider.provideHallService())
        castRoutes(ServiceProvider.provideCastService())
        actorRoutes(ServiceProvider.provideActorService())
        exceptionRoutes()
    }
}
