package com.example.services.hallService

import io.ktor.server.application.Application

import com.example.config.GlobalExeptionsConfig.configureExceptions
import com.example.config.configureAuth
import com.example.config.configureBasicCors
import com.example.config.configureDatabase
import com.example.services.hallService.HallConfiguration.configureCors
import com.example.services.hallService.HallConfiguration.configureRouting

import io.ktor.server.engine.*
import io.ktor.server.netty.*

const val HALL_SERVICE_PORT = 8082
const val HALL_SERVICE_NAME = "hall-service"

fun main(args: Array<String>) {
    embeddedServer(Netty, port = HALL_SERVICE_PORT, module = Application::myApplicationModule).start(wait = true)
}

fun Application.myApplicationModule() {
    configureDatabase()
    configureAuth()
    configureBasicCors()
    configureRouting()
    configureExceptions()
}