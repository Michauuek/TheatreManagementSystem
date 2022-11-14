package com.example.data.request

import kotlinx.serialization.Serializable


@Serializable
data class ReservationRequest(
    val clientName: String,
    val clientEmail: String,
    //TODO Seats prowdopodobnie List<Seat> albo coś takiego
    //TODO HallID
)