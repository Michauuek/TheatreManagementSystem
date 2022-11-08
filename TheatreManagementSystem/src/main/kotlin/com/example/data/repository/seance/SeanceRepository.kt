package com.example.data.repository.seance


import com.example.data.model.Seance
import com.example.data.request.SeanceRequest


interface SeanceRepository {
    suspend fun addSeance(seanceRequest: SeanceRequest): Seance?
    suspend fun getAllSeances(): List<Seance>
}