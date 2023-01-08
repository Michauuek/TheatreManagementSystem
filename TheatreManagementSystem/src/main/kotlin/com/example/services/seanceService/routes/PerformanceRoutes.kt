package com.example.services.seanceService.routes

import com.example.request.seance.PerformanceRequest
import com.example.routes.reservationService.service.performance.PerformanceService
import io.ktor.http.*
import io.ktor.server.application.*
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

            get("/page") {
                val pageNumber: Int? = call.parameters["number"]?.toInt()
                val size: Int? = call.parameters["size"]?.toInt()

                val result = service.getPage(pageNumber, size)
                call.respond(status = HttpStatusCode.OK, result)
            }
            //todo auth
            post("/add") {
                val newPerformance = call.receive<PerformanceRequest>()
                println(newPerformance)
                val result = service.add(newPerformance)
                call.respond(status = HttpStatusCode.Created, result!!)
            }
            delete("/delete/{id}") {
                val id: Int? = call.parameters["id"]?.toInt()
                service.deleteById(id)
                call.respond(status = HttpStatusCode.Accepted, "Performance with id: $id has been deleted")
            }
        }
    }

}