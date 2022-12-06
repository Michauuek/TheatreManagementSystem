package com.example.services.seanceService.service.seance

import com.example.db.model.Seance
import com.example.request.seance.SeanceRequest
import com.example.response.seance.SeanceExtendedResponse

interface SeanceService {
    suspend fun add(seanceRequest: SeanceRequest): Seance
    suspend fun getAll(): List<Seance>
    suspend fun getSeancesBetweenDates(from: String?, to: String?): List<Seance>
    suspend fun getDetailedSeances(): List<SeanceExtendedResponse>
}