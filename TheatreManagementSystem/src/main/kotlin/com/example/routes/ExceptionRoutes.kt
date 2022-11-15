package com.example.routes

import com.example.exception.ParsingException
import com.example.exception.ValidationException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.exceptionRoutes(){
    routing {
        route("/exception") {

            get("/validation") {
                throw ValidationException("Validation exception")
            }

            get("/parsing") {
                throw ParsingException("Parsing exception")
            }

            get("/internal-error") {
                call.respond(
                    HttpStatusCode.InternalServerError
                )
            }

            get("/bad-gateway") {
                call.respond(
                    HttpStatusCode.BadGateway
                )
            }
        }
    }
}