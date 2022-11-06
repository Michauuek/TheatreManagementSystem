package com.example.data.request

import kotlinx.serialization.*

@Serializable
data class UserRequest(
    val id: Int,
    val email: String,
    val password: String
)
