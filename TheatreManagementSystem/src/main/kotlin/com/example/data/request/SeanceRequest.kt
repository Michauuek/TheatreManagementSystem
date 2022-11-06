package com.example.data.request
import kotlinx.serialization.*

@Serializable
data class SeanceRequest(
    val title: String,
    val genre: String,
    val director: String,
    val duration: Int,
    val price: Float,
    /*val scheduleId: Int?,
    val hallId: Int?*/
)