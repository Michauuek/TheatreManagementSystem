package com.example.services.reservationService

import com.example.config.AppExceptionsConfiguration.configureExceptions
import com.example.services.reservationService.config.ReservationConfiguration.configureContentNegotiation
import com.example.services.reservationService.config.ReservationConfiguration.configureCors
import com.example.services.reservationService.config.ReservationConfiguration.configureDatabase
import com.example.services.reservationService.config.ReservationConfiguration.configureRouting
import io.ktor.server.application.Application

import io.ktor.server.engine.*
import io.ktor.server.netty.*


const val RESERVATION_SERVICE_PORT = 8083
const val RESERVATION_SERVICE_NAME = "reservation-service"

fun main(args: Array<String>) {
    embeddedServer(Netty, port = RESERVATION_SERVICE_PORT, module = Application::myApplicationModule).start(wait = true)
}

fun Application.myApplicationModule() {
    configureDatabase()
    configureCors()
    configureContentNegotiation()
    configureRouting()
    configureExceptions()

}