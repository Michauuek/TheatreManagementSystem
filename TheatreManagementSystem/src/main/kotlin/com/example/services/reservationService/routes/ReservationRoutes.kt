package com.example.services.reservationService.routes

import com.example.db.model.Reservation
import com.example.request.reservation.AddReservationRequest
import com.example.services.reservationService.service.reservation.ReservationService
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

            get("/all-reservations/{seanceId}") {
                try {
                    val seanceId = call.parameters["seanceId"]!!.toInt()
                    val result = service.getAllReservationsForSeance(seanceId)
                    call.respond(status = HttpStatusCode.OK, result)
                } catch (e: NumberFormatException) {
                    call.respond(status = HttpStatusCode.BadRequest, listOf<Reservation>())
                }
            }

            post("/add") {
                val newReservation = call.receive<AddReservationRequest>()
                val result = service.add(newReservation)
                call.respond(status = HttpStatusCode.Created, result!!)
            }
        }
    }
}