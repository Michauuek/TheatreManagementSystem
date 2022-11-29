package com.example.routes

import com.example.config.AppConfiguration
import com.example.data.request.SeanceRequest
import com.example.data.service.seance.SeanceService
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import org.postgresql.util.PSQLException
import kotlinx.serialization.Serializable

@Serializable
data class UserSession(val id: String)

fun Application.movieRoutes(service: SeanceService) {

    routing{

        singlePageApplication {
            useResources = true
            filesPath = "client/build"
        }

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

                    call.respondRedirect("http://localhost:3000/")
                }
            }
        }
        post("/add") {
            val newMovie = call.receive<SeanceRequest>()
            try {
                val result = service.add(newMovie)
                call.respond(status = HttpStatusCode.Created, result)
            } catch (e: PSQLException) {
                call.respond(HttpStatusCode.BadRequest, e.message.toString())
            }
        }

        get("/all") {
            val userSession = call.sessions.get("user_session")
            if (userSession != null) {
                val userInfo = AppConfiguration.applicationHttpClient!!.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                    headers {
                        append(HttpHeaders.Authorization, "Bearer ${userSession}")
                    }
                }
                // get all information from userInfo.body() as json
                val body: String = userInfo.body()

                call.respondText("Hello ${body}")
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