package com.example.request.hall

import kotlinx.serialization.*

@Serializable
data class HallRequest(
    val hallName: String,
    val backgroundPath: String,
)
