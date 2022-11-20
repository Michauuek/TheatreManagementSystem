package com.example.data.service.performance

import com.example.data.model.Performance
import com.example.data.request.PerformanceRequest
import com.example.data.response.PerformancePaginatedResponse

interface PerformanceService {
    suspend fun add(performanceRequest: PerformanceRequest): Performance?
    suspend fun getById(id: Int): Performance?
    suspend fun getByTitle(title: String): Performance?
    suspend fun getAll(): List<Performance>
    suspend fun getPage(page: Int?, size: Int?): PerformancePaginatedResponse
}