package com.example.db.model

import kotlinx.serialization.*

@Serializable
data class Cast(
    val id: Int?,
    val actorId: Int,
    val role: String
)
