package com.example.db.model

import kotlinx.serialization.*

@Serializable
data class Cast(
    val performanceId: Int,
    val actorId: Int,
    val role: String
)
