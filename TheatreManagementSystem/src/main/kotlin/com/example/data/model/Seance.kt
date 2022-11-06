package com.example.data.model
import kotlinx.serialization.*

@Serializable
data class Seance(
    val id: Int?,
    val title: String,
    val genre: String,
    val director: String,
    val duration: Int,
    val price: Float,
    /*val scheduleId: Int,
    val hallId: Int*/
)
