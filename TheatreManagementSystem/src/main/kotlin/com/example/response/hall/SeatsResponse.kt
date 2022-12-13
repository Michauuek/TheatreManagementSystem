package com.example.response.hall

import com.example.db.model.Seats
import kotlinx.serialization.Serializable

@Serializable
data class SeatsResponse(
    val HallName: String,
    val Seats: List<Seats>
)

