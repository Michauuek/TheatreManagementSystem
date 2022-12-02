package com.example.config

import com.example.exception.ExceptionResponse
import com.example.exception.ItemNotFoundException
import com.example.exception.ParsingException
import com.example.exception.ValidationException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

object AppExceptionsConfiguration {
    fun Application.configureExceptions() {
        install(StatusPages) {
            exception<Throwable> { call, throwable ->
                when (throwable) {
                    is ValidationException -> {
                        call.respond(
                            HttpStatusCode.BadRequest,
                            ExceptionResponse("${throwable.message}", HttpStatusCode.BadRequest.value)
                        )
                    }
                    is ParsingException -> {
                        call.respond(
                            HttpStatusCode.NotFound,
                            ExceptionResponse("${throwable.message}", HttpStatusCode.ExpectationFailed.value)
                        )
                    }
                    is ItemNotFoundException -> {
                        call.respond(
                            HttpStatusCode.NotFound,
                            ExceptionResponse("${throwable.message}", HttpStatusCode.ExpectationFailed.value)
                        )
                    }
                }
            }

            status(
                // any number of status codes can be mentioned
                HttpStatusCode.InternalServerError,
                HttpStatusCode.BadGateway,
            ) { call, statusCode ->
                when(statusCode) {
                    HttpStatusCode.InternalServerError -> {
                        call.respond(
                            HttpStatusCode.InternalServerError,
                            ExceptionResponse("Internal server error", HttpStatusCode.InternalServerError.value)
                        )
                    }
                    HttpStatusCode.BadGateway -> {
                        call.respond(
                            HttpStatusCode.BadGateway,
                            ExceptionResponse("Bad gateway", HttpStatusCode.BadGateway.value)
                        )
                    }
                }
            }
        }
    }
}