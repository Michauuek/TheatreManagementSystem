package com.example.db.model

data class Reservation(
    val id: Int?,
    val reservationDate: String,
    val reservationTime: String,
    val clientName: String,
    val clientEmail: String,
    val reservedSeats: List<Seats>?,
)