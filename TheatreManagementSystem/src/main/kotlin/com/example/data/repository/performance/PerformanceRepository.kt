package com.example.data.repository.performance

import com.example.data.model.Hall
import com.example.data.model.Performance
import com.example.data.model.Seats
import com.example.data.request.GetSeatsRequest
import com.example.data.request.HallRequest
import com.example.data.request.PerformanceRequest

interface PerformanceRepository {
    suspend fun add(performanceRequest: PerformanceRequest): Performance?
    suspend fun getById(id: Int): Performance?
    suspend fun getByName(performanceName: String): Performance?
    suspend fun getAll(): List<Performance>
    suspend fun getPage(page: Int, size: Int): List<Performance>
}