package com.example.response.seance

import com.example.db.model.Performance

import kotlinx.serialization.Serializable


@Serializable
data class PerformancePaginatedResponse(
    val performances: List<Performance>,
    val pageSize: Int,
    val currentPage: Int,
    val totalElements: Int
)
