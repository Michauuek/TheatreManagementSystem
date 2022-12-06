package com.example.services.hallService

import com.example.services.hallService.HallServiceConfig.configureService
import io.ktor.server.engine.*
import io.ktor.server.netty.*


fun main(args: Array<String>) {
    embeddedServer(Netty, port = HallServiceConfig.HALL_SERVICE_PORT)
    {
        configureService()
    }.start(wait = true)
}