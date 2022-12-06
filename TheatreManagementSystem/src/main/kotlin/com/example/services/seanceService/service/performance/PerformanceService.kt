package com.example.routes.reservationService.service.performance

import com.example.db.model.Performance
import com.example.request.seance.PerformanceRequest
import com.example.response.seance.PerformancePaginatedResponse

interface PerformanceService {
    suspend fun add(performanceRequest: PerformanceRequest): Performance?
    suspend fun getById(id: Int): Performance?
    suspend fun getByTitle(title: String): Performance?
    suspend fun getAll(): List<Performance>
    suspend fun getPage(page: Int?, size: Int?): PerformancePaginatedResponse
}