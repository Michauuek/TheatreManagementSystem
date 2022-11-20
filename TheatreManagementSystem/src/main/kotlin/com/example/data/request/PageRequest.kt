package com.example.data.request

import kotlinx.serialization.*

@Serializable
data class PageRequest(
    val size: Int?,
    val pageNumber: Int?
)
