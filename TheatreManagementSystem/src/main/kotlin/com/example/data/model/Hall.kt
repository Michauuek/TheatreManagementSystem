package com.example.data.model

import kotlinx.serialization.*

@Serializable
data class Hall(
    val id: Int?,
    val number: String,
    val initialSeatsAmount: Int,
    val availableSeatsAmount: Int
)
