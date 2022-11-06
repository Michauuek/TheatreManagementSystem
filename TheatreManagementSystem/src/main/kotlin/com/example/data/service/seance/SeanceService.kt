package com.example.data.service.seance

import com.example.data.model.Seance
import com.example.data.request.SeanceRequest

interface SeanceService {
    suspend fun addSeance(seanceRequest: SeanceRequest): Seance
    suspend fun getAllSeances(): List<Seance>
}