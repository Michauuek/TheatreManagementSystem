package com.example.data.service.seance

import com.example.data.model.Seance
import com.example.data.repository.seance.SeanceRepository
import com.example.data.request.SeanceRequest
import io.ktor.utils.io.errors.*


class SeanceServiceImpl(
    private val seanceRepository: SeanceRepository
): SeanceService {

    override suspend fun add(seanceRequest: SeanceRequest): Seance {
        return seanceRepository.addSeance(seanceRequest) ?: throw IOException("User could not be added.")
    }

    override suspend fun getAll(): List<Seance> =
        seanceRepository.getAllSeances()
}