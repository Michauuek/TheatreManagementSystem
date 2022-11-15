package com.example.data.model
import kotlinx.serialization.*

@Serializable
data class Seance(
    val id: Int?,
    val hallName: String,
    val performanceId: Int,
    val seanceDate: String,
    val seanceTime: String,
)
