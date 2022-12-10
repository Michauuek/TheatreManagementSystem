package com.example.db.model

import kotlinx.serialization.*

@Serializable
data class ReservedSeats (
    val reservationId: Int,
    val seatId: Int
)
