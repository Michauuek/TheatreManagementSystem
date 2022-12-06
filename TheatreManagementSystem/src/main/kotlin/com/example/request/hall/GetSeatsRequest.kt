package com.example.request.hall

import kotlinx.serialization.*

@Serializable
data class GetSeatsRequest(
    val hallName: String,
)
