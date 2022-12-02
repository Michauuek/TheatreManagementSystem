package com.example.routes

import com.example.config.AppConfiguration
import com.example.data.service.auth.OAuth2Response
import com.example.data.service.auth.UserSession
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

suspend fun getAuthorizationTokenFromCode(code: UserSession): HttpResponse {
    val params = Parameters.build {
        append("code", code.id)
        append("client_id", System.getenv("CLIENT_ID"))
        append("client_secret", System.getenv("CLIENT_SECRET"))
        append("redirect_uri", "http://localhost:8080")
        append("grant_type", "authorization_code")
    }.formUrlEncode();

    val response = AppConfiguration.applicationHttpClient!!.post("https://oauth2.googleapis.com/token") {
        contentType(ContentType.Application.FormUrlEncoded)
        accept(ContentType.Application.Json)
        setBody(
            params
        )
    }
    return response;
}

fun Application.authRoutes() {
    routing {
        route("/auth")
        {
            authenticate("admin") {
                get("/callback")
                {
                    val principal: OAuthAccessTokenResponse.OAuth2? = call.principal()

                    println("google's callback: " + principal?.accessToken.toString())

                    call.sessions.set(UserSession(principal?.accessToken.toString()))
                }
            }
            post("/login") {
                val userSession: UserSession = call.receive()

                println("[INFO] User tries to login: $userSession")

                val response = getAuthorizationTokenFromCode(userSession)

                // check if response is ok
                if (response.status.value == 200) {
                    val token = Json.decodeFromString<OAuth2Response>(response.bodyAsText())

                    println("User logged $token")
                    call.sessions.set(UserSession(token.access_token))
                    
                    // val tokentext = response.bodyAsText()
                    // println("User logged $tokentext")
                    // println("User logged ${response.headers}")

                    call.respond(HttpStatusCode.OK)
                } else {
                    println("Spierdalaj")
                    call.respond(HttpStatusCode.Unauthorized)
                }
            }
        }
    }
}