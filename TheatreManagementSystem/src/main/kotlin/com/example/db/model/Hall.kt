package com.example.db.model

import kotlinx.serialization.*

@Serializable
data class Hall(
    val hallName: String,
    val backgroundPath: String,
    val seatScale: Double,
    // TODO: width
    // TODO: height
    // TODO: seatSize

)
