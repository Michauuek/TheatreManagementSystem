package com.example.services.hallService

import com.example.db.DatabaseFactory
import com.example.routes.reservationService.repository.hall.HallRepositoryImpl
import com.example.routes.reservationService.routes.hallRoutes
import com.example.routes.reservationService.service.hall.HallServiceImpl
import io.ktor.server.application.*

object HallServiceConfig {
    const val HALL_SERVICE_PORT = 8082
    const val HALL_SERVICE_NAME = "hall-service"

    fun Application.configureService() {
        DatabaseFactory.init()

        hallRoutes(HallServiceImpl(HallRepositoryImpl()))
    }
}