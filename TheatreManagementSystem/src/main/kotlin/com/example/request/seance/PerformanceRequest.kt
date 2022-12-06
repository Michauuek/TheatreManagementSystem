package com.example.request.seance
import kotlinx.serialization.*

@Serializable
data class PerformanceRequest(
    val title: String,
    val description: String,
    val imageUrl: String,
    val castId: Int
)
