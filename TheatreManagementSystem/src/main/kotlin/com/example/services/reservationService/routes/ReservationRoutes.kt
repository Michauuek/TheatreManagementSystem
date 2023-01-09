package com.example.services.reservationService.routes

import com.example.auth.auth
import com.example.auth.authInfo
import com.example.auth.fromJson
import com.example.config.getHttpClient
import com.example.db.model.Reservation
import com.example.request.reservation.AddReservation
import com.example.request.reservation.AddReservationRequest
import com.example.request.reservation.AddReservationOauthRequest
import com.example.response.auth.Privilege
import com.example.response.reservation.AllReservations
import com.example.response.seance.SeanceExtendedResponse
import com.example.services.reservationService.service.reservation.ReservationService
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.header
import kotlinx.serialization.decodeFromString


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
                val seanceId = call.parameters["seanceId"]!!.toInt();

                auth {
                    try {
                        val result = service.getAllReservationsForSeance(seanceId)
                        call.respond(status = HttpStatusCode.OK, result)
                    } catch (e: Exception) {
                        call.respond(
                            status = HttpStatusCode.BadRequest,
                            AllReservations(seanceId = seanceId, reservations = listOf())
                        )
                    }
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

                println("newReservation: $newReservation")

                val numberOfSeats = newReservation.reservedSeats.size;
                val seance: SeanceExtendedResponse = call.application
                    .getHttpClient()
                    .get("http://localhost:8084/seance/get-detailed?id=${newReservation.seanceId}")
                    .fromJson()
                val price = seance.price * numberOfSeats

                val reservation = AddReservation(
                    newReservation.seanceId,
                    newReservation.clientName,
                    newReservation.clientEmail,
                    newReservation.clientPhone,
                    "IP",
                    "Form",
                    newReservation.reservedSeats,
                    price
                )


                val result = service.add(reservation)

                if(result != null)
                    call.respond(status = HttpStatusCode.Created, result)
                else
                    call.respond(status = HttpStatusCode.BadRequest, "Reservation not added")
            }

            // add with Google autorization instead of form, take user data from auth
            post("/add-auth") {

                val newReservation: AddReservationOauthRequest = kotlinx.serialization.json.Json.decodeFromString(call.receiveText());

                // get user data from auth
                val user = authInfo();

                println("newReservation: $newReservation $user")

                if (user == null) {
                    call.respond(status = HttpStatusCode.BadRequest, "Reservation not added")
                    return@post
                }

                val numberOfSeats = newReservation.reservedSeats.size;
                val seance: SeanceExtendedResponse = call.application
                    .getHttpClient()
                    .get("http://localhost:8084/seance/get-detailed?id=${newReservation.seanceId}")
                    .fromJson()
                val price = seance.price * numberOfSeats

                val reservation = AddReservation(
                    newReservation.seanceId,
                    user.familyName,
                    user.email,
                    null,
                    "IP",
                    "oauth",
                    newReservation.reservedSeats,
                    price
                )

                val result = service.add(reservation)

                if(result != null)
                    call.respond(status = HttpStatusCode.Created, result)
                else
                    call.respond(status = HttpStatusCode.BadRequest, "Reservation not added")
            }

            delete("/delete/{id}") {
                val id: Int? = call.parameters["id"]?.toInt()
                service.deleteById(id)
                call.respond(status = HttpStatusCode.Accepted, "Reservation with id: $id has been deleted")
            }
        }
    }
}