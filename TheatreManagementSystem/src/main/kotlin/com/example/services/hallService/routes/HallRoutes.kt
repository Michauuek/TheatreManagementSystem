package com.example.routes.reservationService.routes

import com.example.request.hall.HallRequest
import com.example.routes.reservationService.service.hall.HallService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.hallRoutes(service: HallService) {

    routing{
        route("/hall"){

            get("/all") {
                val result = service.getAll()
                call.respond(status = HttpStatusCode.OK, result)
            }

            post("/add") {
                val newHall = call.receive<HallRequest>()
                val result = service.add(newHall)
                call.respond(status = HttpStatusCode.Created, result!!)
            }
        }
    }

}