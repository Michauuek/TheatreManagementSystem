package com.example.data.model

import kotlinx.serialization.*

@Serializable
data class Hall(
    val HallName: String,
    val SeatsLayout: String,
    val BackgroundPath: String,
)
