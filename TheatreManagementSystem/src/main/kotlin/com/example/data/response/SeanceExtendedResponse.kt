package com.example.data.response

import kotlinx.serialization.Serializable


@Serializable
data class SeanceExtendedResponse(
    val id: Int?,
    val hallName: String,
    val performanceId: Int,
    val seanceDate: String,
    val seanceTime: String,
    val title: String,
    val description: String,
    val castId: Int
)