package com.example.routes

import com.example.data.request.SeanceRequest
import com.example.data.service.auth.UserSession
import com.example.data.service.auth.auth
import com.example.data.service.seance.SeanceService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.postgresql.util.PSQLException


fun Application.movieRoutes(service: SeanceService) {
    routing {
        singlePageApplication {
            filesPath = "client/build"
        }

        route("/seance") {
            post("/add") {
                val newMovie = call.receive<SeanceRequest>()
                auth {
                    try {
                        val result = service.add(newMovie)
                        call.respond(status = HttpStatusCode.Created, result)
                    } catch (e: PSQLException) {
                        call.respond(HttpStatusCode.BadRequest, e.message.toString())
                    }
                }
            }

            get("/all") {
                // get session
                val session: UserSession? = call.sessions.get()
                println("outside auth block")
                auth {
                    println("inside auth block")
                    val result = service.getAll()
                    call.respond(status = HttpStatusCode.OK, result)
                }
            }
            get("/all-detailed") {
                val result = service.getDetailedSeances()
                call.respond(status = HttpStatusCode.OK, result)
            }
            get("/get-seances-range") {
                val from: String? = call.parameters["from"]
                val to: String? = call.parameters["to"]

                val result = service.getSeancesBetweenDates(from, to)
                call.respond(status = HttpStatusCode.OK, result)
            }
        }
    }
}