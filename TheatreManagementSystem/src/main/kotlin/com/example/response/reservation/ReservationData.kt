package com.example.response.reservation

import kotlinx.serialization.Serializable

@Serializable
data class ReservationData(
    val seanceId: Int,
    val reservationDate: String,
    val reservationTime: String,
    val clientName: String,
    val clientEmail: String,
    val clientPhone: String?,
    val reservationIPAddress: String,
    val reservationAuthMode: String,
    val reservedSeats: List<Int>,
    val totalPrice: Double,
)
