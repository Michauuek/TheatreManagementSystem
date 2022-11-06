package com.example.data.service.seance

import com.example.data.model.Seance
import com.example.data.request.SeanceRequest

interface SeanceService {
    suspend fun add(seanceRequest: SeanceRequest): Seance
    suspend fun getAll(): List<Seance>
}