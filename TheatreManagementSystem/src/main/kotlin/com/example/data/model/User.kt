package com.example.data.model

data class User(
    val id: Int,
    val email: String,
    val password: String,
    var authToken: String? = null
)

