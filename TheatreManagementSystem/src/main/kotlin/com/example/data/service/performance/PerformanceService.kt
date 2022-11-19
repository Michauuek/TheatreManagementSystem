package com.example.data.service.performance

import com.example.data.model.Actor
import com.example.data.model.Performance
import com.example.data.request.ActorRequest
import com.example.data.request.PerformanceRequest

interface PerformanceService {
    suspend fun add(performanceRequest: PerformanceRequest): Performance?
    suspend fun getById(id: Int): Performance?
    suspend fun getByTitle(title: String): Performance?
    suspend fun getAll(): List<Performance>
}