package com.example.routes

import com.example.config.AppConfiguration
import com.example.data.request.SeanceRequest
import com.example.data.service.seance.SeanceService
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
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

suspend fun getAutorizationTokenFromCode(code: UserSession): HttpResponse {
    val params = Parameters.build {
        append("code", code.id)
        append("client_id", System.getenv("CLIENT_ID"))
        append("client_secret", System.getenv("CLIENT_SECRET"))
        append("redirect_uri", "http://localhost:8080/seance/auth/callback")
        append("grant_type", "authorization_code")
    }.formUrlEncode();

    println(params);

    val response = AppConfiguration.applicationHttpClient!!.post("https://accounts.google.com/o/oauth2/token") {
        contentType(ContentType.Application.FormUrlEncoded)
        accept(ContentType.Application.Json)
        
        setBody(
            params
        )
    }

    return response;
}

@Serializable
data class UserSession(val id: String)
fun Application.movieRoutes(service: SeanceService) {
    routing{

        singlePageApplication {
            filesPath = "client/build"
        }

        route("/seance"){
         authenticate("admin") {
            route("/auth") {
                get("/") {

                }
                get("/callback")
                {
                    val principal: OAuthAccessTokenResponse.OAuth2? = call.principal()

                    println("google's callback: "+ principal?.accessToken.toString())

                    call.sessions.set(UserSession(principal?.accessToken.toString()))


                }
            }
        }

        post("/login"){
            // get autorization code
            val userSession: UserSession = call.receive()

            println(userSession)
            // get oauth2 token from google by ktor's oauth2

            val response = getAutorizationTokenFromCode(userSession)

            println(response)
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
            // get session
            val session: UserSession? = call.sessions.get()

            if (session != null) {
                val userInfo = AppConfiguration.applicationHttpClient!!.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                    headers {
                        append(HttpHeaders.Authorization, "Bearer ${session.id}")
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