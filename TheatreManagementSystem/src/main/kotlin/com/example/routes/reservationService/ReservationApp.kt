package com.example.reservationService

import com.example.reservationService.repository.hall.HallRepositoryImpl
import com.example.reservationService.routes.hallRoutes
import com.example.reservationService.service.hall.HallServiceImpl
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8081, module = Application::configure).start(wait = true)
}

fun Application.configure() {
    hallRoutes(HallServiceImpl(HallRepositoryImpl()))
}
