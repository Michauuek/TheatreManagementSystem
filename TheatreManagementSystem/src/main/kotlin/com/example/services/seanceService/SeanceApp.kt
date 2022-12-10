package com.example.services.seanceService

import com.example.config.AppExceptionsConfiguration.configureExceptions
import com.example.services.seanceService.config.SeanceConfiguration.configureContentNegotiation
import com.example.services.seanceService.config.SeanceConfiguration.configureCors
import com.example.services.seanceService.config.SeanceConfiguration.configureDatabase
import com.example.services.seanceService.config.SeanceConfiguration.configureRouting
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
    configureCors()
    configureContentNegotiation()
    configureRouting()
    configureExceptions()
}


