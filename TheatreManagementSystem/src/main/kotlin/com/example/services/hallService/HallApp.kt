package com.example.services.hallService

import com.example.config.AppExceptionsConfiguration.configureExceptions
import io.ktor.server.application.Application

import com.example.services.reservationService.config.HallConfiguration.configureContentNegotiation
import com.example.services.reservationService.config.HallConfiguration.configureCors
import com.example.services.reservationService.config.HallConfiguration.configureDatabase
import com.example.services.reservationService.config.HallConfiguration.configureRouting

import io.ktor.server.engine.*
import io.ktor.server.netty.*

const val HALL_SERVICE_PORT = 8082
const val HALL_SERVICE_NAME = "hall-service"

fun main(args: Array<String>) {
    embeddedServer(Netty, port = HALL_SERVICE_PORT, module = Application::myApplicationModule).start(wait = true)
}

fun Application.myApplicationModule() {
    configureDatabase()
    configureCors()
    configureContentNegotiation()
    configureRouting()
    configureExceptions()
}