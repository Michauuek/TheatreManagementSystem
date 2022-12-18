package com.example.services.seanceService.routes

import com.example.request.seance.ActorRequest
import com.example.services.seanceService.service.actor.ActorService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.actorRoutes(service: ActorService) {
    routing {
        route("/actor"){
            get("/all") {
                val result = service.getAll()
                call.respond(status = HttpStatusCode.OK, result)
            }

            get("/all/{id}") {
                val id = call.parameters["id"]?.toInt()
                val result = service.getById(id!!)
                call.respond(status = HttpStatusCode.OK, result!!)
            }

            get("/by-performance-id/{id}") {
                val id = call.parameters["id"]?.toInt()
                val result = service.getByPerformanceId(id!!)
                call.respond(status = HttpStatusCode.OK, result)
            }

            post("/add") {
                val newActor = call.receive<ActorRequest>()
                val result = service.add(newActor)
                call.respond(status = HttpStatusCode.Created, result!!)
            }
        }
    }
}