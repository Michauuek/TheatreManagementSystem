package com.example.routes

import com.example.data.request.SeanceRequest
import com.example.data.service.seance.SeanceService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.movieRoutes(service: SeanceService) {

    routing{
        route("/seance"){
            get("/") {
                call.respondText("Hello Seances!")
            }

            get("/all") {
                val result = service.getAllSeances()
                call.respond(status = HttpStatusCode.OK, result)
            }

            post("/add") {
                val newMovie = call.receive<SeanceRequest>()
                val result = service.addSeance(newMovie)
                call.respond(status = HttpStatusCode.OK, result)
            }
        }
    }

}