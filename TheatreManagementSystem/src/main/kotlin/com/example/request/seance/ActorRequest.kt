package com.example.request.seance

import kotlinx.serialization.Serializable

@Serializable
data class ActorRequest(
    val name: String,
    val surname: String,
    val photoUrl: String
)
