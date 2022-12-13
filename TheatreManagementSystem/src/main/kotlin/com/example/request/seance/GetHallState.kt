package com.example.request.seance

import kotlinx.serialization.Serializable

@Serializable
data class GetHallState(
    val seanceId: Int,
)