package com.example.services.authService


import com.example.config.GlobalExeptionsConfig.configureExceptions
import com.example.config.configureAuth
import com.example.config.configureDatabase
import io.ktor.server.application.Application
import com.example.config.configureBasicCors
import com.example.services.authService.AuthConfiguration.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*

const val AUTH_SERVICE_PORT = 8081
const val AUTH_SERVICE_NAME = "auth-service"

fun main(args: Array<String>) {
    embeddedServer(Netty, port = AUTH_SERVICE_PORT, module = Application::myApplicationModule).start(wait = true)
}

fun Application.myApplicationModule() {
    configureDatabase()
    configureAuth()
    configureBasicCors()
    configureRouting()
    configureExceptions()
}

