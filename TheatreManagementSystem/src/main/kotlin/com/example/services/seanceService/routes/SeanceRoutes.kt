package com.example.services.seanceService.routes

import com.example.auth.auth
import com.example.exception.ItemNotFoundException
import com.example.request.seance.SeanceRequest
import com.example.services.seanceService.service.seance.SeanceService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.postgresql.util.PSQLException


fun Application.movieRoutes(service: SeanceService) {
    routing {
        route("/seance") {
            //todo auth (DONE)
            post("/add") {
                val newMovie = call.receive<SeanceRequest>()
                auth {
                    try {
                        val result = service.add(newMovie)
                        call.respond(status = HttpStatusCode.Created, result)
                    } catch (e: PSQLException) {
                        call.respond(HttpStatusCode.BadRequest, e.message.toString())
                    }
                }
            }
            // todo can be removed? / internal?
            get("/all") {
                val result = service.getAll()

                call.respond(status = HttpStatusCode.OK, result)
            }
            get("/all-detailed") {
                val result = service.getDetailedSeances()
                call.respond(status = HttpStatusCode.OK, result)
            }
            get("/get-detailed") {
                val id_string: String? = call.parameters["id"]
                val id : Int? = id_string?.toInt()
                val result = service.getDetailed(id) ?: throw ItemNotFoundException("Seance with provided id not found.")
                call.respond(status = HttpStatusCode.OK, result)
            }
            get("/get-seances-range") {
                val from: String? = call.parameters["from"]
                val to: String? = call.parameters["to"]

                val result = service.getSeancesBetweenDates(from, to)
                call.respond(status = HttpStatusCode.OK, result)
            }
            get("/get-seances-range-by-performance-id") {
                val from: String? = call.parameters["from"]
                val to: String? = call.parameters["to"]
                val id: Int? = call.parameters["id"]?.toInt()

                val result = service.getSeancesBetweenDatesByPerformanceId(from, to, id)
                call.respond(status = HttpStatusCode.OK, result)
            }
            get("/get-detailed-seances-range") {
                val from: String? = call.parameters["from"]
                val to: String? = call.parameters["to"]

                val result = service.getDetailedSeancesBetweenDates(from, to)
                call.respond(status = HttpStatusCode.OK, result)
            }
        }
    }
}