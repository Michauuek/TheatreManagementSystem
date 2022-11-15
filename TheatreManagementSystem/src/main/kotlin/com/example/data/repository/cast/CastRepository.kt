package com.example.data.repository.cast

import com.example.data.model.Actor
import com.example.data.model.Cast
import com.example.data.request.ActorRequest
import com.example.data.request.CastRequest

interface CastRepository {
    suspend fun add(castRequest: CastRequest): Cast?
    suspend fun getById(id: Int): Cast?
    suspend fun getAll(): List<Cast>
}