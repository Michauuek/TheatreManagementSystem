package com.example.data.request

import kotlinx.serialization.Serializable


@Serializable
data class ReservationRequest(
    val clientName: String,
    val clientEmail: String,
    val reservedSeats: String,
    // TODO Other fields that wll carry information about payment method ect.
)