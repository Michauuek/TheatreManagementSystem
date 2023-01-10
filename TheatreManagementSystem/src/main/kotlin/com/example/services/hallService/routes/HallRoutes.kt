package com.example.services.hallService.routes

import com.example.config.getHttpClient
import com.example.request.hall.HallAddRequest
import com.example.services.hallService.service.HallService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.hallRoutes(service: HallService) {
    routing {
        route("/hall") {
            get("/all") {
                val result = service.getAll()

                call.respond(status = HttpStatusCode.OK, result)
            }

            get("/{hallName}") {
                val hallName = call.parameters["hallName"]!!
                val result = service.getByName(hallName)!!

                call.respond(status = HttpStatusCode.OK, result)
            }

            get("/with-status") {
                val id = call.parameters["id"]?.toInt()!!
                val result = service.getByNameWithSeats(id)

                call.respond(status = HttpStatusCode.OK, result)
            }
        }
    }
}