package com.example.data.model

import kotlinx.serialization.*

@Serializable
data class Hall(
    val hallName: String,
    val backgroundPath: String,
)
