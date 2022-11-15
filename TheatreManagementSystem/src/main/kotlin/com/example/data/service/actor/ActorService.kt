package com.example.data.service.actor

import com.example.data.model.Actor
import com.example.data.request.ActorRequest

interface ActorService {
    suspend fun add(actorRequest: ActorRequest): Actor?
    suspend fun getById(id: Int): Actor?
    suspend fun getAll(): List<Actor>
}