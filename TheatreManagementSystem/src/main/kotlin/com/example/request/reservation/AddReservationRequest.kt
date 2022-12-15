package com.example.request.reservation

import kotlinx.serialization.Serializable


@Serializable
data class AddReservationRequest(
    val clientName: String,
    val clientEmail: String,
    val reservedSeats: String,
    val seanceId: Int,
    val phoneNumber: String,
    // TODO Other fields that wll carry information about payment method ect.
)