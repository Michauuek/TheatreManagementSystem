package com.example.services.seanceService.service.actor

import com.example.db.model.Actor
import com.example.services.seanceService.repository.actor.ActorRepository
import com.example.request.seance.ActorRequest
import com.example.exception.ItemNotFoundException
import com.example.exception.ValidationException

class ActorServiceImpl(
    private val actorRepository: ActorRepository
): ActorService {
    override suspend fun add(actorRequest: ActorRequest): Actor? {
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

    override suspend fun getById(id: Int): Actor? {
        if(actorRepository.getById(id) == null)
            throw ItemNotFoundException("Actor with provided id not found.")
        return actorRepository.getById(id)
    }

    override suspend fun getAll(): List<Actor> {
        return actorRepository.getAll().ifEmpty { emptyList() }
    }
}