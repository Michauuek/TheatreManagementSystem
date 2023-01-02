package com.example.db.model

import kotlinx.serialization.*

@Serializable
data class Reservation(
    val id: Int?,
    val seanceId: Int,
    val reservationDate: String,
    val reservationTime: String,
    val clientName: String,
    val clientEmail: String,
    val clientPhone: String?,
    val reservationIPAddress: String,
    val reservationAuthMode: String,
    val reservedSeats: List<Seats>
)