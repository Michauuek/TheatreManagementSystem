package com.example.db.model

import kotlinx.serialization.Serializable

@Serializable
data class Performance(
    val performanceId: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val castId: Int?
)
