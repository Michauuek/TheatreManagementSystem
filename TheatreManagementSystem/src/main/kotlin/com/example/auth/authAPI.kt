package com.example.auth

import com.example.config.getHttpClient
import com.example.response.auth.Privilege
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
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
 * If authorization fails it responds with 401 Unauthorized. If authorization succeeds it executes the block inside.
 *
 * Usage:
 * ```kt
 * auth {
 *     // do admin stuff
 *     call.respond(status = HttpStatusCode.OK, "Authorized")
 * }
 * ```
 */
suspend inline fun PipelineContext<Unit, ApplicationCall>.auth(crossinline body: PipelineInterceptor<Unit, ApplicationCall>): Boolean {
    val session = call.request.headers["user_session"];

    if (session == null) {
        call.respond(status = HttpStatusCode.Unauthorized, "Not logged in")
        return false;
    }

    val responsePrivilage: Privilege = call.application.getHttpClient().get("http://localhost:8081/auth/privileges") {
        header("user_session", session)
    }.fromJson();

    if(responsePrivilage == Privilege.ADMIN)
    {
        body.invoke(this, Unit)
        return true;
    }
    else
    {
        call.respond(HttpStatusCode.Unauthorized, "Not enough privileges")
        return false;
    }
}


suspend inline fun PipelineContext<Unit, ApplicationCall>.softAuth(crossinline body: PipelineInterceptor<Unit, ApplicationCall>): Boolean {
    val responsePrivilage: Privilege = call.application.getHttpClient().get("http://localhost:8081/auth/privileges") {
        header("user_session", call.request.headers["user_session"]?: return false)
    }.fromJson();

    return if(responsePrivilage == Privilege.ADMIN)
    {
        body.invoke(this, Unit)
        true;
    }
    else
    {
        false;
    }
}

suspend inline fun PipelineContext<Unit, ApplicationCall>.actorAuth(crossinline body: PipelineInterceptor<Unit, ApplicationCall>): Boolean {
    val responsePrivilage: Privilege = call.application.getHttpClient().get("http://localhost:8081/auth/privileges") {
        header("user_session", call.request.headers["user_session"]?: return false)
    }.fromJson();

    return if(responsePrivilage == Privilege.ACTOR || responsePrivilage == Privilege.ADMIN)
    {
        body.invoke(this, Unit)
        true;
    }
    else
    {
        false;
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

suspend inline fun<reified T>  ApplicationCall.fromJson(): T {
    val body = this.receiveText();
    return kotlinx.serialization.json.Json.Default.decodeFromString(body)
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
