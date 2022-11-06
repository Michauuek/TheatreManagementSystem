package com.example.routes

import com.example.data.request.HallRequest
import com.example.data.request.ScheduleRequest
import com.example.data.service.hall.HallService
import com.example.data.service.schedule.ScheduleService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.scheduleRoutes(service: ScheduleService) {

    routing{
        route("/schedule"){
            get("/") {
                call.respondText("Hello Halls!")
            }

            get("/all") {
                val result = service.getAll()
                call.respond(status = HttpStatusCode.OK, result)
            }

            post("/add") {
                val newSchedule = call.receive<ScheduleRequest>()
                val result = service.add(newSchedule)
                call.respond(status = HttpStatusCode.OK, result!!)
            }
        }
    }

}