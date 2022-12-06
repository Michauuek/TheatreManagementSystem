package com.example.services.seanceService

import com.example.db.DatabaseFactory
import io.ktor.server.engine.*
import io.ktor.server.netty.*

const val SEANCE_SERVICE_PORT = 8084
const val SEANCE_SERVICE_NAME = "seance-service"

fun main(args: Array<String>) {
    embeddedServer(Netty, port = SEANCE_SERVICE_PORT)
    {
        DatabaseFactory.init()
    }.start(wait = true)
}