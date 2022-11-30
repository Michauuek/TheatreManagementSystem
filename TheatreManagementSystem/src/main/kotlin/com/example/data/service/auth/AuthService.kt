package com.example.data.service.auth

import com.example.config.AppConfiguration
import com.example.data.model.Actor
import com.example.data.repository.actor.ActorRepository
import com.example.data.request.ActorRequest
import com.example.exception.ItemNotFoundException
import com.example.exception.ValidationException
import io.ktor.client.call.*
import io.ktor.client.request.*
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
// create crossinline function that takes an block of code and checks if in call session is not null
suspend inline fun PipelineContext<Unit, ApplicationCall>.auth(crossinline body: PipelineInterceptor<Unit, ApplicationCall>) {
    val session: UserSession? = call.sessions.get()

    if (session != null) {
        println("User is 'loged'" + session.id)
        val userInfo =
            AppConfiguration.applicationHttpClient!!.get("https://www.googleapis.com/oauth2/v2/userinfo") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${session.id}")
                }
            }
        val text: String = userInfo.body()

        println("User info: $text")

        //todo check if user is admin

        // use body
        body.invoke(this, Unit)
    } else {
        println("user is not 'loged'")
        call.respond(HttpStatusCode.Unauthorized)
    }
}
