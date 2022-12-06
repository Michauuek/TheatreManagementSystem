package com.example.request.seance

import kotlinx.serialization.*

@Serializable
data class PageRequest(
    val size: Int?,
    val pageNumber: Int?
)
