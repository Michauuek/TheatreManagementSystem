package com.example.services.seanceService.routes

import com.example.auth.UserSession
import com.example.auth.auth
import com.example.request.seance.SeanceRequest
//import com.example.services.authService.service.auth.UserSession
//import com.example.services.authService.service.auth.auth
import com.example.services.seanceService.service.seance.SeanceService
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

                val result = service.getAll()
                call.respond(status = HttpStatusCode.OK, result)

//                println("outside auth block")
//                auth {
//                    println("inside auth block")
//                    val result = service.getAll()
//                    call.respond(status = HttpStatusCode.OK, result)
//                }
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