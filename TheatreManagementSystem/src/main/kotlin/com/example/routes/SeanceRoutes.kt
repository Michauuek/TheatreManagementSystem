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
                val result = service.getAllSeances()
                call.respond(status = HttpStatusCode.OK, result)
            }
            //Test cvomnewa
            post("/add") {
                val newMovie = call.receive<SeanceRequest>()
                val result = service.addSeance(newMovie)
                call.respond(status = HttpStatusCode.OK, result)
            }
        }
    }

}