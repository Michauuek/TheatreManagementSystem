package com.example.request.reservation

import kotlinx.serialization.Serializable

@Serializable
data class AddReservationOauthRequest(
    val seanceId: Int,
    val reservedSeats: List<Int>,
)