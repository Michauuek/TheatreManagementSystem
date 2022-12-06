package com.example.services.reservationService

import com.example.services.reservationService.ReservationServiceConfig.configureService
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port = ReservationServiceConfig.RESERVATION_SERVICE_PORT)
    {
        configureService()
    }.start(wait = true)
}