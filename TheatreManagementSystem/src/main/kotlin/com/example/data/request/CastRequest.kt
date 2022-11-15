package com.example.data.request

import kotlinx.serialization.Serializable

@Serializable
data class CastRequest(
    val actorId: Int,
    val role: String
)
