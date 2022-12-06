package com.example.services.seanceService.repository.performance

import com.example.db.model.Performance
import com.example.request.seance.PerformanceRequest

interface PerformanceRepository {
    suspend fun add(performanceRequest: PerformanceRequest): Performance?
    suspend fun getById(id: Int): Performance?
    suspend fun getByName(performanceName: String): Performance?
    suspend fun getAll(): List<Performance>
    suspend fun getPage(page: Int, size: Int): List<Performance>
}