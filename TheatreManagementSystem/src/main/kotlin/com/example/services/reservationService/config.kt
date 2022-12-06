package com.example.services.reservationService

import com.example.db.DatabaseFactory
import io.ktor.server.application.*

object ReservationServiceConfig {
    const val RESERVATION_SERVICE_PORT = 8083
    const val RESERVATION_SERVICE_NAME = "reservation-service"

    fun Application.configureService() {
        DatabaseFactory.init()

        //todo
    }
}