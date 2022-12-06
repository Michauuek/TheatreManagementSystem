package com.example.routes

import com.example.request.seance.CastRequest
import com.example.services.seanceService.service.cast.CastService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.castRoutes(service: CastService) {

    routing{
        route("/cast"){
            get("/all") {
                val result = service.getAll()
                call.respond(status = HttpStatusCode.OK, result)
            }

            get("/all/{id}") {
                val id = call.parameters["id"]?.toInt()
                val result = service.getById(id!!)
                call.respond(status = HttpStatusCode.OK, result!!)
            }

            post("/add") {
                val newCast = call.receive<CastRequest>()
                val result = service.add(newCast)
                call.respond(status = HttpStatusCode.Created, result!!)
            }
        }
    }

}