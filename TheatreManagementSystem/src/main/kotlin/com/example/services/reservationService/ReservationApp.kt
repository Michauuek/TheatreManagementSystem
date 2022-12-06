package com.example.services.reservationService

import com.example.db.DatabaseFactory
import io.ktor.server.engine.*
import io.ktor.server.netty.*


const val RESERVATION_SERVICE_PORT = 8083
const val RESERVATION_SERVICE_NAME = "reservation-service"

fun main(args: Array<String>) {
    embeddedServer(Netty, port = RESERVATION_SERVICE_PORT)
    {
        DatabaseFactory.init()
    }.start(wait = true)
}