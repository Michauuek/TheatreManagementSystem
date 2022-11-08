package com.example.data.request
import kotlinx.serialization.*

@Serializable
data class SeanceRequest(
    val HallId: Int,
    val PerformanceId: Int,
    val seanceDate: String,
    val seanceTime: String,
)