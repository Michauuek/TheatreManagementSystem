package com.example.services.seanceService


import com.example.services.seanceService.SeanceServiceConfig.configureService
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port = SeanceServiceConfig.SEANCE_SERVICE_PORT)
    {
        configureService()
    }.start(wait = true)
}