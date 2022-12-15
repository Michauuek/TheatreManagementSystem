package com.example.request.reservation

import kotlinx.serialization.*

//todo wtf?
@Serializable
data class AddReservedSeats(
    val SeatId: Int,
)
