package com.example.config

import com.example.data.db.DatabaseFactory
import com.example.di.ServiceProvider
import com.example.routes.movieRoutes
import com.example.routes.userRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*


object AppConfiguration {
    fun configureDatabase() {
        DatabaseFactory.init()
    }

    fun Application.configureContentNegotiation() {
        install(ContentNegotiation) {
            jackson()
        }
    }


    fun Application.configureRouting() {
        movieRoutes(ServiceProvider.provideSeanceService())
        userRoutes(ServiceProvider.provideUserService())
    }
}