package com.example.routes

import com.example.config.AppConfiguration
import com.example.data.request.SeanceRequest
import com.example.data.service.seance.SeanceService
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.application.*
import io.ktor.server.auth.*

data class UserSession(val id: String)

fun Application.movieRoutes(service: SeanceService) {

    routing{
        route("/seance"){
            authenticate("admin") {
                route("/auth") {
                    get("/") {
                        val session = call.sessions.get<UserSession>()
                        if (session != null) {
                            call.respond(session.id)
                        } else {
                            call.respond("Hello Guest")
                        }
                    }
                    get("/callback")
                    {
                        val principal: OAuthAccessTokenResponse.OAuth2? = call.principal()
                        call.sessions.set(UserSession(principal?.accessToken.toString()))
                        //call.respondText("Hello World")
                        call.respondRedirect("http://localhost:3000/")
                    }
                }
            }
            post("/add") {
                val newMovie = call.receive<SeanceRequest>()
                val result = service.add(newMovie)
                call.respond(status = HttpStatusCode.Created, result)
            }

            get("/all") {
                val userSession: UserSession? = call.sessions.get()
                if (userSession != null) {
                    val userInfo: UserSession = AppConfiguration.applicationHttpClient!!.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                        headers {
                            append(HttpHeaders.Authorization, "Bearer ${userSession.id}")
                        }
                    }.body()
                    call.respondText("Hello, ${userInfo.id}!")
                } else {
                    call.respondRedirect("/seance/auth/")
                }

                val result = service.getAll()
                call.respond(status = HttpStatusCode.OK, result)
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