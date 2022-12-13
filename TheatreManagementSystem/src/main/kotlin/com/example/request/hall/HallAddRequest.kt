package com.example.request.hall

import kotlinx.serialization.*

@Serializable
data class HallAddRequest(
    val hallName: String,
    val backgroundPath: String,
)
