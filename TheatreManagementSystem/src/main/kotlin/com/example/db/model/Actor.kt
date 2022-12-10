package com.example.db.model

import kotlinx.serialization.*

@Serializable
data class Actor(
    val id: Int?,
    val name: String,
    val surname: String,
    val photoUrl: String
)
