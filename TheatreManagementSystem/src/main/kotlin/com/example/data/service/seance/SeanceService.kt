package com.example.data.service.seance

import com.example.data.model.Seance
import com.example.data.request.SeanceRequest
import com.example.data.response.SeanceExtendedResponse

interface SeanceService {
    suspend fun add(seanceRequest: SeanceRequest): Seance
    suspend fun getAll(): List<Seance>
    suspend fun getSeancesBetweenDates(from: String?, to: String?): List<Seance>
    suspend fun getDetailedSeances(): List<SeanceExtendedResponse>
}