package com.example.services.seanceService.service.actor

import com.example.db.model.Actor
import com.example.exception.ItemNotFoundException
import com.example.exception.ValidationException
import com.example.request.seance.ActorRequest
import com.example.response.actor.ActorCastResponse
import com.example.services.seanceService.repository.actor.ActorRepository

class ActorService(
    private val actorRepository: ActorRepository
) {
    suspend fun add(actorRequest: ActorRequest): Actor? {
        if(actorRequest.name.isBlank() || actorRequest.surname.isBlank())
            throw ValidationException("Both name and surname cannot be empty")
        
        val isActorNotExists: Boolean = actorRepository
            .getAll()
            .none { actor ->
                    actor.name == actorRequest.name && actor.surname == actorRequest.surname }

        if(!isActorNotExists)
            throw ValidationException("Actor with provided name already exists")
        return actorRepository.add(actorRequest)
    }

    suspend fun getById(id: Int): Actor? {
        if(actorRepository.getById(id) == null)
            throw ItemNotFoundException("Actor with provided id not found.")
        return actorRepository.getById(id)
    }

    suspend fun getAll(): List<Actor> {
        return actorRepository.getAll().ifEmpty { emptyList() }
    }

    suspend fun getByPerformanceId(id: Int): List<ActorCastResponse> {
        return actorRepository.getByPerformanceId(id)
    }
}