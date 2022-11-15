package com.example.data.request

import kotlinx.serialization.*

@Serializable
data class GetSeatsRequest(
    val hallName: String,
)
