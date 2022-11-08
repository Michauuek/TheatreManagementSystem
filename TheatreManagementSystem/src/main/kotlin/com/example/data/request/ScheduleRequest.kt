package com.example.data.request

import kotlinx.serialization.*

@Serializable
data class ScheduleRequest(
    val seanceDate: String,
    val seanceTime: String,
)