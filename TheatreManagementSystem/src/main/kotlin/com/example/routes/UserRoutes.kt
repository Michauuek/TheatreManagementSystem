package com.example.routes

import com.example.data.request.UserRequest
import com.example.data.service.user.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.userRoutes(service: UserService) {
    routing {
        route("/user") {
            get("/getByEmail") {
                val email = call.receive<String>()
                val result = service.getByEmail(email)
                call.respond(status = HttpStatusCode.OK, result)
            }

            post("/register") {
                val newUser = call.receive<UserRequest>()
                val result = service.register(newUser)
                call.respond(status = HttpStatusCode.OK, result)
            }
        }
    }
}