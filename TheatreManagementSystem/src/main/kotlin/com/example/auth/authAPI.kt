package com.example.auth

import com.example.config.getHttpClient
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
import kotlinx.serialization.decodeFromString

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
//User info: {
//    "id": "102077978367439224068",
//    "email": "zlotymaciej@gmail.com",
//    "verified_email": true,
//    "name": "Maciej 'Lord225' Złotorowicz",
//    "given_name": "Maciej 'Lord225'",
//    "family_name": "Złotorowicz",
//    "picture": "https://lh3.googleusercontent.com/a/AEdFTp74W5NFw5VoCLUhuu4pjA24Je20_IskHBf2zsY=s96-c",
//    "locale": "pl"
//}
@Serializable
data class GoogleUserInfo(
    val id: String,
    val email: String,
    @SerialName("verified_email") val verifiedEmail: Boolean,
    val name: String,
    @SerialName("given_name") val givenName: String,
    @SerialName("family_name") val familyName: String,
    val picture: String,
    val locale: String,
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
    val googleUserInfo = authInfo()

    // check if user is logged in
    if (googleUserInfo != null)
    {
        println("User is 'loged'$googleUserInfo")

        // todo check if user is admin (ask database) !!!!!! IMPORTANT !!!!!!

        // use body
        body.invoke(this, Unit)
    }
    else
    {
        println("User is not 'loged'")
        call.respond(HttpStatusCode.Unauthorized)
    }
}

/**
 * # fromJson
 * It is just like `.body` but **works**.
 */
suspend inline fun<reified T> HttpResponse.fromJson(): T {
    val body = this.bodyAsText();
    return kotlinx.serialization.json.Json.decodeFromString(body)
}

/*
    * # Google OAuth2 authentication provider
    * This function returns user info if user is logged in, null otherwise.
 */
suspend fun PipelineContext<Unit, ApplicationCall>.authInfo(): GoogleUserInfo? {
    val session = call.sessions.get("user_session")

    // transform session to UserSession
    val userSession = session as UserSession?

    val client = call.application.getHttpClient()

    return if (userSession != null) {
        val userInfo =
            client.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${userSession.id}")
                }
            }
        userInfo.fromJson()
    } else {
        null
    }
}
