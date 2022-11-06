package com.example.data.request

import kotlinx.serialization.*

@Serializable
data class HallRequest(
    val number: String,
    val initialSeatsAmount: Int,
    val availableSeatsAmount: Int
)
