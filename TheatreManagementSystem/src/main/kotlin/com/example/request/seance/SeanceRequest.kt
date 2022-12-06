package com.example.request.seance
import kotlinx.serialization.*

@Serializable
data class SeanceRequest(
    val hallName: String,
    val performanceId: Int,
    val seanceDate: String,
    val seanceTime: String,
)