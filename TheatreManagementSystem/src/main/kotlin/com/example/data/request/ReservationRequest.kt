package com.example.data.request

import kotlinx.serialization.Serializable

@Serializable
data class ReservationRequest(
    val ClientName: String,
    val ClientEmail: String,
    //TODO Seats?
)