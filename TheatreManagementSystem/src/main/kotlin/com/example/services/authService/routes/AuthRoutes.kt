package com.example.routes

import com.example.auth.OAuth2Response
import com.example.auth.UserSession
import com.example.config.AppConfiguration
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
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
                // user calls with auth code from google
                val userSession: UserSession = call.receive()

                println("[INFO] User tries to login: $userSession")

                // we try to get access token from google based on code from user
                val response = getAuthorizationTokenFromCode(userSession)

                // check if google returned access token
                if (response.status.value == 200) {
                    val token = Json.decodeFromString<OAuth2Response>(response.bodyAsText())

                    // if we have access token it means that user is authenticated and we can check if he is an admin

                    call.sessions.set(UserSession(token.access_token))

                    call.respond(HttpStatusCode.OK)
                }

                // if google returned error we return error to user as he is not authenticated
                call.respond(HttpStatusCode.Unauthorized)
            }
        }
    }
}