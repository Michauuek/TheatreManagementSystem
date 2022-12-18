package com.example.response.hall

import kotlinx.serialization.Serializable

@Serializable
data class SeatsWithStateResponse(
    val seatsResponse: SeatsResponse,
    val reservedSeats: List<Int>,
)