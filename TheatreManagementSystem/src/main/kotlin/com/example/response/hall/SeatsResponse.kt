package com.example.response.hall

import com.example.db.model.SimpleSeats
import kotlinx.serialization.Serializable

@Serializable
data class SeatsResponse(
    val HallName: String,
    val Seats: List<SimpleSeats>
)

