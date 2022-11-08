package com.example.data.request

import kotlinx.serialization.*

@Serializable
data class HallRequest(
    val HallName: String,
    val SeatsLayout: String,
    val BackgroundPath: String,
)
