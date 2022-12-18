package com.example.services.seanceService.repository.actor

import com.example.db.model.Actor
import com.example.request.seance.ActorRequest
import com.example.response.actor.ActorCastResponse

interface ActorRepository {
    suspend fun add(actorRequest: ActorRequest): Actor?
    suspend fun getById(id: Int): Actor?
    suspend fun getAll(): List<Actor>
    suspend fun getByPerformanceId(id: Int): List<ActorCastResponse>
}