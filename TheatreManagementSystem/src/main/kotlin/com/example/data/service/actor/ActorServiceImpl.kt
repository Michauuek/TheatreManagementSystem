package com.example.data.service.actor

import com.example.data.model.Actor
import com.example.data.repository.actor.ActorRepository
import com.example.data.request.ActorRequest
import io.ktor.utils.io.errors.*

class ActorServiceImpl(
    private val actorRepository: ActorRepository
): ActorService{
    override suspend fun add(actorRequest: ActorRequest): Actor? {
        if(actorRequest.name.isNullOrBlank() || actorRequest.surname.isNullOrBlank())
            throw IOException("Both name and surname cannot be empty")
        return actorRepository.add(actorRequest)
    }

    override suspend fun getById(id: Int): Actor? {
        return actorRepository.getById(id)
    }

    override suspend fun getAll(): List<Actor> {
        return actorRepository.getAll().ifEmpty { emptyList() }
    }
}