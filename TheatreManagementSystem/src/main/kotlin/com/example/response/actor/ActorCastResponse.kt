package com.example.response.actor

import kotlinx.serialization.Serializable

@Serializable
data class ActorCastResponse (
    val actorId: Int?,
    val name: String,
    val surname: String,
    val photoUrl: String,
    val performanceId: Int,
    val role: String,
)