package com.example.response.hall

import com.example.db.model.SimpleSeats
import kotlinx.serialization.Serializable

@Serializable
data class SeatsResponse(
    val hallName: String,
    val background: String,
    val seats: List<SimpleSeats>
)

