package com.example.auth

import com.example.services.authService.authConfig.AuthConfiguration
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSession(val id: String)

@Serializable
data class OAuth2Response(
    @SerialName("access_token") val access_token: String,
    @SerialName("expires_in") val expires_in: Long,
    @SerialName("refresh_token") val refresh_token: String?,
    @SerialName("scope") val scope: String,
    @SerialName("token_type") val token_type: String,
    @SerialName("id_token")  val id_token: String,
)

/**
 * # Google OAuth2 authentication provider
 * This block is responsible for handling google oauth2 authentication. It checks if user is logged in and if he is an admin.
 *
 * Usage:
 * ```kt
 * auth {
 *     // Critical code
 *
 *     call.respond(HttpStatusCode.OK, "You are logged in as admin")
 * }
 * call.respond(HttpStatusCode.Unauthorized, "You are not logged in as admin")
 * ```
 */
suspend inline fun PipelineContext<Unit, ApplicationCall>.auth(crossinline body: PipelineInterceptor<Unit, ApplicationCall>) {
    val session: UserSession? = call.sessions.get()

    if (session != null) {
        println("User is 'loged'" + session.id)
        val userInfo =
            AuthConfiguration.applicationHttpClient!!.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${session.id}")
                }
            }
        val text: String = userInfo.body()

        println("User info: $text")

        //todo check if user is admin (ask database)

        // use body
        body.invoke(this, Unit)
    } else {
        println("user is not 'loged'")
        call.respond(HttpStatusCode.Unauthorized)
    }
}
