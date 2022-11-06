package com.example.data.request

import kotlinx.serialization.*

@Serializable
data class UserLoginRequest(
    val email: String,
    val password: String
)