package com.example.routes

import com.example.data.request.HallRequest
import com.example.data.request.PerformanceRequest
import com.example.data.service.hall.HallService
import com.example.data.service.performance.PerformanceService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.performanceRoutes(service: PerformanceService) {

    routing{
        route("/performance"){
            get("/{id}") {
                val id = call.parameters["id"]?.toInt()
                val result = service.getById(id!!)
                call.respond(status = HttpStatusCode.OK, result!!)
            }

            get("/title/{title}") {
                val title = call.parameters["title"]
                val result = service.getByTitle(title!!)
                call.respond(status = HttpStatusCode.OK, result!!)
            }

            get("/all") {
                val result = service.getAll()
                call.respond(status = HttpStatusCode.OK, result)
            }

            post("/add") {
                val newPerformance = call.receive<PerformanceRequest>()
                val result = service.add(newPerformance)
                call.respond(status = HttpStatusCode.Created, result!!)
            }
        }
    }

}