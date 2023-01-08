package com.example.services.reservationService

import com.example.config.GlobalExceptionsConfig.configureExceptions
import com.example.config.configureAuth
import com.example.config.configureBasicCors
import com.example.config.configureDatabase
import com.example.services.reservationService.ReservationConfiguration.configureRouting

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
    configureAuth()
    configureBasicCors()
    configureRouting()
    configureExceptions()
}