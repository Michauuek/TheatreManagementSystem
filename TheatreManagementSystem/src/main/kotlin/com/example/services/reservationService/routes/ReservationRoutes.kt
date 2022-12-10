package com.example.services.reservationService.routes

import com.example.services.reservationService.service.reservation.ReservationService
import com.example.request.reservation.ReservationRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*



fun Application.reservationRoutes(service: ReservationService) {
    routing {
        route("/reservation"){
            get("/all") {
                val result = service.getAll()
                call.respond(status = HttpStatusCode.OK, result)
            }


            post("/add") {
                val newReservation = call.receive<ReservationRequest>()
                val result = service.add(newReservation)
                call.respond(status = HttpStatusCode.Created, result!!)
            }
        }
    }
}