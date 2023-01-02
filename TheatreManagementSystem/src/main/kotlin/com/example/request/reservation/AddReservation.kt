package com.example.request.reservation

import com.example.db.model.Seats
import kotlinx.serialization.Serializable

@Serializable
data class AddReservation(
    val seanceId: Int,
    val clientName: String,
    val clientEmail: String,
    val clientPhone: String?,
    val reservationIPAddress: String,
    val reservationAuthMode: String,
    val reservedSeats: List<Int>
)