package com.example.db.model

import kotlinx.serialization.Serializable

@Serializable
data class Seats(
    val id: Int?,
    val hallName: String,
    val seatName: String,
    val posX: Float,
    val posY: Float,
)