package com.example.routes

import com.example.data.request.UserRequest
import com.example.data.service.user.UserService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.userRoutes(service: UserService) {

    routing {
        route("/user"){
            get("/getByEmail") {
                val email = call.receive<String>()
                val result = service.getByEmail(email)
                call.respond(status = HttpStatusCode.OK, result!!)
            }

            post("/register") {
                val newUser = call.receive<UserRequest>()
                val result = service.registerUser(newUser)
                call.respond(status = HttpStatusCode.OK, result!!)
            }
        }
    }

}