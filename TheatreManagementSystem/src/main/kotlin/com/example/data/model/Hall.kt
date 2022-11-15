package com.example.data.model

import kotlinx.serialization.*

@Serializable
data class Hall(
    val HallName: String,
    val BackgroundPath: String,
)
