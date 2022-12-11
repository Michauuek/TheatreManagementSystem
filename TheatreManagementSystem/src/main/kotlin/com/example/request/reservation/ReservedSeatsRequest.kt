package com.example.request.reservation

import kotlinx.serialization.*

@Serializable
data class ReservedSeatsRequest(
    val SeatId: Int,
)
