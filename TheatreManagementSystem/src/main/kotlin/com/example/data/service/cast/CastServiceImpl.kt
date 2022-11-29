package com.example.data.service.cast

import com.example.data.model.Actor
import com.example.data.model.Cast
import com.example.data.repository.actor.ActorRepository
import com.example.data.repository.cast.CastRepository
import com.example.data.request.ActorRequest
import com.example.data.request.CastRequest
import com.example.exception.ItemNotFoundException
import io.ktor.server.plugins.*

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