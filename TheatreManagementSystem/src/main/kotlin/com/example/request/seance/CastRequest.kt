package com.example.request.seance

import kotlinx.serialization.Serializable

@Serializable
data class CastRequest(
    val actorId: Int,
    val role: String
)
