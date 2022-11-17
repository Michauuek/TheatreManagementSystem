package com.example.routes

import com.example.data.request.SeanceRequest
import com.example.data.service.seance.SeanceService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.movieRoutes(service: SeanceService) {

    routing{
        route("/seance"){
            get("/") {
                call.respondText("Hello Seances!")
            }

            get("/all") {
                val result = service.getAll()
                call.respond(status = HttpStatusCode.OK, result)
            }
            get("/all-detailed") {
                val result = service.getDetailedSeances()
                call.respond(status = HttpStatusCode.OK, result)
            }
            get("/get-seances-range") {
                val from: String? = call.parameters["from"]
                val to: String? = call.parameters["to"]

                val result = service.getSeancesBetweenDates(from, to)
                call.respond(status = HttpStatusCode.OK, result)
            }

            post("/add") {
                val newMovie = call.receive<SeanceRequest>()
                val result = service.add(newMovie)
                call.respond(status = HttpStatusCode.Created, result)
            }
        }
    }

}