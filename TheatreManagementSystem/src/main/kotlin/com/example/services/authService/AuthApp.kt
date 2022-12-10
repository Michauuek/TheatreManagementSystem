package com.example.services.authService


import com.example.config.AppExceptionsConfiguration.configureExceptions
import io.ktor.server.application.Application
import com.example.services.authService.config.AuthConfiguration.configureAuth
import com.example.services.authService.config.AuthConfiguration.getHttpClient
import com.example.services.seanceService.config.SeanceConfiguration.configureContentNegotiation
import com.example.services.seanceService.config.SeanceConfiguration.configureCors
import com.example.services.seanceService.config.SeanceConfiguration.configureDatabase
import com.example.services.seanceService.config.SeanceConfiguration.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*

const val AUTH_SERVICE_PORT = 8081
const val AUTH_SERVICE_NAME = "auth-service"

fun main(args: Array<String>) {
    embeddedServer(Netty, port = AUTH_SERVICE_PORT, module = Application::myApplicationModule).start(wait = true)
}

fun Application.myApplicationModule() {
    configureDatabase()
    configureCors()
    configureContentNegotiation()
    configureRouting()
    configureExceptions()
    configureAuth()
    getHttpClient()
}

