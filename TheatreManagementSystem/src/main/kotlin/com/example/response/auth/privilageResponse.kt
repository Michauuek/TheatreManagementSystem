package com.example.response.auth

import kotlinx.serialization.Serializable


@Serializable
enum class Privilege {
    ADMIN,
    ACTOR,
    GUEST,
    NONE,
}