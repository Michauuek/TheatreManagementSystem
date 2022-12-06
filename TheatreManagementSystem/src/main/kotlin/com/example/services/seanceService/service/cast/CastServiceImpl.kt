package com.example.services.seanceService.service.cast

import com.example.db.model.Cast
import com.example.services.seanceService.repository.actor.ActorRepository
import com.example.services.seanceService.repository.cast.CastRepository
import com.example.request.seance.CastRequest
import com.example.exception.ItemNotFoundException

class CastServiceImpl(
    private val castRepository: CastRepository,
    private val actorRepository: ActorRepository
): CastService {

    override suspend fun add(castRequest: CastRequest): Cast? {
        if(actorRepository.getById(castRequest.actorId) == null)
            throw ItemNotFoundException("Actor with provided id not found.")
        return castRepository.add(castRequest)
    }

    override suspend fun getById(id: Int): Cast? {
        if(castRepository.getById(id) == null)
            throw ItemNotFoundException("Cast with provided id not found.")
        return castRepository.getById(id)
    }

    override suspend fun getAll(): List<Cast> {
        return castRepository.getAll().ifEmpty { emptyList() }
    }
}