package com.example.services.seanceService.service.cast

import com.example.db.model.Cast
import com.example.services.seanceService.repository.actor.ActorRepository
import com.example.services.seanceService.repository.cast.CastRepository
import com.example.request.seance.CastRequest
import com.example.exception.ItemNotFoundException

class CastService(
    private val castRepository: CastRepository,
    private val actorRepository: ActorRepository
){

    suspend fun add(castRequest: CastRequest): Cast? {
        if(actorRepository.getById(castRequest.actorId) == null)
            throw ItemNotFoundException("Actor with provided id not found.")
        return castRepository.add(castRequest)
    }

    suspend fun getById(id: Int): Cast? {
        if(castRepository.getById(id) == null)
            throw ItemNotFoundException("Cast with provided id not found.")
        return castRepository.getById(id)
    }

    suspend fun getAll(): List<Cast> {
        return castRepository.getAll().ifEmpty { emptyList() }
    }
}