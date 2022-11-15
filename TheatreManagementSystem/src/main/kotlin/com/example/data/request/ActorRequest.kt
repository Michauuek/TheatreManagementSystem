package com.example.data.request

import kotlinx.serialization.Serializable

@Serializable
data class ActorRequest(
    val name: String,
    val surname: String
)
