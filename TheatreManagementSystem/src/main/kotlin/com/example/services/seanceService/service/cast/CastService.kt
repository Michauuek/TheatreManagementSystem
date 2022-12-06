package com.example.services.seanceService.service.cast

import com.example.db.model.Cast
import com.example.request.seance.CastRequest

interface CastService {
    suspend fun add(castRequest: CastRequest): Cast?
    suspend fun getById(id: Int): Cast?
    suspend fun getAll(): List<Cast>
}