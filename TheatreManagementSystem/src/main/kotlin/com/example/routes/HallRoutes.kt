package com.example.routes

import com.example.data.request.HallRequest
import com.example.data.request.SeanceRequest
import com.example.data.service.hall.HallService
import com.example.data.service.seance.SeanceService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.hallRoutes(service: HallService) {

    routing{
        route("/hall"){
            get("/") {
                call.respondText("Hello Halls!")
            }

            get("/all") {
                val result = service.getAll()
                call.respond(status = HttpStatusCode.OK, result)
            }

            post("/add") {
                val newHall = call.receive<HallRequest>()
                val result = service.add(newHall)
                call.respond(status = HttpStatusCode.OK, result!!)
            }
        }
    }

}