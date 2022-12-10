package com.example.services.seanceService

import com.example.services.seanceService.config.seancesConfiguration.configureContentNegotiation
import com.example.services.seanceService.config.seancesConfiguration.configureCors
import com.example.services.seanceService.config.seancesConfiguration.configureDatabase
import com.example.services.seanceService.config.seancesConfiguration.configureRouting
import com.example.services.seanceService.config.seancesConfiguration.configureSession
import io.ktor.server.engine.*
import io.ktor.server.netty.*

const val SEANCE_SERVICE_PORT = 8084
const val SEANCE_SERVICE_NAME = "seance-service"
// TODO: Split seance service to seance service and actor service
fun main(args: Array<String>) {
    embeddedServer(Netty, port = SEANCE_SERVICE_PORT) {
        configureCors()
        configureRouting()
        configureDatabase()
        configureSession()
        configureContentNegotiation()


    }.start(wait = true)
}