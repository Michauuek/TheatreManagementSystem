package com.example.services.seanceService

import com.example.config.GlobalExeptionsConfig.configureExceptions
import com.example.config.configureAuth
import com.example.config.configureBasicCors
import com.example.config.configureDatabase
import com.example.services.seanceService.SeanceConfiguration.configureCors
import com.example.services.seanceService.SeanceConfiguration.configureRouting
import io.ktor.server.application.Application
import io.ktor.server.engine.*
import io.ktor.server.netty.*

const val SEANCE_SERVICE_PORT = 8084
const val SEANCE_SERVICE_NAME = "seance-service"

fun main(args: Array<String>) {
    embeddedServer(Netty, port = SEANCE_SERVICE_PORT, module = Application::myApplicationModule).start(wait = true)
}

fun Application.myApplicationModule() {
    configureDatabase()
    configureAuth()
    configureBasicCors()
    configureRouting()
    configureExceptions()
}
