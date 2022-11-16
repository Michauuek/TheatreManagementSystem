package com.example.data.model

data class Reservation(
    val id: Int?,
    val reservationDate: String,
    val reservationTime: String,
    val clientName: String,
    val clientEmail: String,
    //TODO Seats[] ?
)