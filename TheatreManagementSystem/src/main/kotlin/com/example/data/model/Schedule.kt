package com.example.data.model

import kotlinx.serialization.*
import java.sql.Date

@Serializable
data class Schedule(
    val id: Int?,
    val seanceDate: String,
    val seanceTime: String,
)
