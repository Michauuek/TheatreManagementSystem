package com.example.data.request
import kotlinx.serialization.*

@Serializable
data class SeanceRequest(
    val hallId: String,
    val performanceId: Int,
    val seanceDate: String,
    val seanceTime: String,
)