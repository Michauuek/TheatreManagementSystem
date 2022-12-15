package com.example.request.reservation

import kotlinx.serialization.*

@Serializable
data class ReservedSeats(
    val SeatId: Int,
)
