package com.example.response.reservation

import com.example.db.model.Reservation
import kotlinx.serialization.Serializable

@Serializable
data class AllReservations(
    val seanceId: Int,
    val reservations: List<Reservation>,
)