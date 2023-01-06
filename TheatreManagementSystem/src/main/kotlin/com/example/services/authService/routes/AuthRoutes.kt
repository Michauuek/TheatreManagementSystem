package com.example.services.authService.routes

import com.example.auth.OAuth2Response
import com.example.auth.UserSession
import com.example.auth.auth
import com.example.auth.authInfo
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
import com.example.config.getHttpClient
import com.example.services.authService.service.AuthService
import io.ktor.client.*
import io.ktor.client.call.*

suspend fun getAuthorizationTokenFromCode(code: UserSession, client: HttpClient): HttpResponse {
    val params = Parameters.build {
        append("code", code.id)
        append("client_id", System.getenv("CLIENT_ID"))
        append("client_secret", System.getenv("CLIENT_SECRET"))
        append("redirect_uri", "http://localhost:8080")
        append("grant_type", "authorization_code")
    }.formUrlEncode();

    val response =  client.post("https://oauth2.googleapis.com/token") {
        contentType(ContentType.Application.FormUrlEncoded)
        accept(ContentType.Application.Json)
        setBody(
            params
        )
    }
    return response;
}

fun Application.authRoutes(service: AuthService) {
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

            get("/privileges") {
                val userSession = authInfo();

                call.respond(service.getPrivilege(userSession))

            }

            post("/login") {
                // user calls with auth code from google
                val userSession: UserSession = call.receive()

                println("[INFO] User tries to login: $userSession")

                // we try to get access token from google based on code from user
                val response = getAuthorizationTokenFromCode(userSession, call.application.getHttpClient())

                // check if google returned access token
                if (response.status.value == 200) {
                    val token = Json.decodeFromString<OAuth2Response>(response.bodyAsText())

                    // set cookie with access token for all ports
                    call.sessions.set(UserSession(token.access_token))

                    println("[INFO] User is logged in: $token")

                    call.respond(HttpStatusCode.OK)
                }

                // if google returned error we return error to user as he is not authenticated
                call.respond(HttpStatusCode.Unauthorized)
            }

//            get("/test") {
//                println("test")
//                auth {
//                    println("test2")
//                    call.respond(HttpStatusCode.OK, "You are logged in as admin")
//                }
//                println("test3")
//                call.respond(HttpStatusCode.Unauthorized, "You are not logged in as admin")
//            }
        }
    }
}