package com.example.data.request

import kotlinx.serialization.*

@Serializable
data class HallRequest(
    val hallName: String,
    val backgroundPath: String,
)
