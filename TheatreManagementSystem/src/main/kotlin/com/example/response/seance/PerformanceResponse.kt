package com.example.response.seance

data class PerformanceResponse(
    val performanceId: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val length: Int,
)
