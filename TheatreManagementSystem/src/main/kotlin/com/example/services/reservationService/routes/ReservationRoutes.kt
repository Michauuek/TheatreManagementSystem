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
import com.example.response.reservation.ReservationStatus
import com.example.response.seance.SeanceExtendedResponse
import com.example.services.reservationService.service.reservation.CalculatePriceService
import com.example.services.reservationService.service.reservation.ReservationService
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.reservationRoutes(service: ReservationService, calculatePriceService: CalculatePriceService) {
    routing {
        route("/reservation")
        {
            // auth done
            get("/all") {
                auth {
                    val result = service.getAll()
                    call.respond(status = HttpStatusCode.OK, result)
                }
            }

            // todo cors (internal only)
            get("/all-reservations/{seanceId}") {
                auth {
                    val seanceId = call.parameters["seanceId"]!!.toInt();

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

            //auth done
            get("/all-reserved-seats/{seanceId}")
            {
                //todo require permissions if seanceId is old
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
                val newReservation: AddReservationRequest = call.receive()

                val ip = call.request.origin.remoteHost

                val reservation = AddReservation(
                    newReservation.seanceId,
                    newReservation.clientName,
                    newReservation.clientEmail,
                    newReservation.clientPhone,
                    ip,
                    "Form",
                    newReservation.reservedSeats,
                    calculatePriceService.calculatePrice(newReservation.seanceId, newReservation.reservedSeats)
                )

                val result = service.add(reservation)

                println("newReservation: $newReservation result: $result")

                if(result.status == ReservationStatus.OK)
                    call.respond(status = HttpStatusCode.Created, result)
                else
                    call.respond(status = HttpStatusCode.BadRequest, "Reservation not added")
            }

            // add with Google autorization instead of form, take user data from auth
            post("/add-auth") {
                val newReservation: AddReservationOauthRequest = call.receive()

                // get user data from auth
                val user = authInfo();

                if (user == null) {
                    call.respond(status = HttpStatusCode.BadRequest, "Reservation not added")
                    return@post
                }

                val ip = call.request.origin.remoteHost

                val reservation = AddReservation(
                    newReservation.seanceId,
                    user.familyName,
                    user.email,
                    null,
                    ip,
                    "oauth",
                    newReservation.reservedSeats,
                    calculatePriceService.calculatePrice(newReservation.seanceId, newReservation.reservedSeats)
                )


                val result = service.add(reservation)

                println("newReservation: $newReservation $user result: $result")

                if(result.status == ReservationStatus.OK)
                    call.respond(status = HttpStatusCode.Created, result)
                else
                    call.respond(status = HttpStatusCode.BadRequest, result)
            }

            delete("/delete/{id}") {
                auth {
                    val id: Int? = call.parameters["id"]?.toInt()
                    service.deleteById(id)
                    call.respond(status = HttpStatusCode.Accepted, "Reservation with id: $id has been deleted")
                }
            }
        }
    }
}