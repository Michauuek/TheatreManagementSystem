package com.example.request.reservation

import kotlinx.serialization.Serializable


@Serializable
data class AddReservationRequest(
    val seanceId: Int,
    val clientName: String,
    val clientEmail: String,
    val clientPhone: String,
    val reservedSeats: List<Int>,
)