package com.example.db.model

import kotlinx.serialization.*

@Serializable
data class OauthWhileList(
    val id: Int,
    val email: String,
    val role: String
)
