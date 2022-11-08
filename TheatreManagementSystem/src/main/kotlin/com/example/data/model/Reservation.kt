package com.example.data.model

data class Reservation(
    val id: Int?,
    val ReservationDate: String,
    val ReservationTime: String,
    val ClientName: String,
    val ClientEmail: String,
)