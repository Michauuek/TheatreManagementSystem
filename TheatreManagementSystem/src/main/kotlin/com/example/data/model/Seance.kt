package com.example.data.model
import kotlinx.serialization.*

//TODO Małe litery
@Serializable
data class Seance(
    val id: Int?,
    val HallId: String,
    val PerformanceId: Int,
    val seanceDate: String,
    val seanceTime: String,
)
