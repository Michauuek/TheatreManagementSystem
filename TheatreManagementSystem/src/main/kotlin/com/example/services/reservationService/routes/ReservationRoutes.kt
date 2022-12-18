package com.example.services.reservationService.routes

import com.example.db.model.Reservation
import com.example.request.reservation.AddReservationRequest
import com.example.services.reservationService.service.reservation.ReservationService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*



fun Application.reservationRoutes(service: ReservationService) {
    routing {
        route("/reservation"){
            // todo auth
            get("/all") {
                val result = service.getAll()
                call.respond(status = HttpStatusCode.OK, result)
            }

            // todo cors (internal only)
            get("/all-reservations/{seanceId}") {
                try {
                    val seanceId = call.parameters["seanceId"]!!.toInt();
                    val result = service.getAllReservationsForSeance(seanceId)
                    call.respond(status = HttpStatusCode.OK, result)
                } catch (e: Exception) {
                    call.respond(status = HttpStatusCode.BadRequest, listOf<Reservation>())
                }
            }
            get("/all-reserved-seats/{seanceId}")
            {
                try {
                    val seanceId = call.parameters["seanceId"]!!.toInt();
                    val result = service.getAllReservedSeatsForSeance(seanceId)
                    call.respond(status = HttpStatusCode.OK, result)
                } catch (e: Exception) {
                    call.respond(status = HttpStatusCode.BadRequest, listOf<Reservation>())
                }
            }
            // ?
            post("/add") {
                // get reservation info
                val newReservation = call.receive<AddReservationRequest>()
                // todo get caller ip, should be included in database for security reasons
                val host = call.request.origin.remoteHost

                println(host) // todo

                val result = service.add(newReservation)
                call.respond(status = HttpStatusCode.Created, result!!)
            }

            // add with Google autorization instead of form
            post("/add-auth") {
                call.respond(status = HttpStatusCode.BadRequest, "Not implemented")
                // get reservation info
                val newReservation = call.receive<AddReservationRequest>()
                // todo get caller ip, should be included in database for security reasons
                val host = call.request.origin.remoteHost

                val result = service.add(newReservation)
            }
        }
    }
}