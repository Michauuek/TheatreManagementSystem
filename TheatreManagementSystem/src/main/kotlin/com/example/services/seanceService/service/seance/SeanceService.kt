package com.example.services.seanceService.service.seance

import com.example.db.model.Seance
import com.example.request.seance.SeanceRequest
import com.example.response.seance.SeanceExtendedResponse

interface SeanceService {
    suspend fun add(seanceRequest: SeanceRequest): Seance
    suspend fun getAll(): List<Seance>
    suspend fun getSeancesBetweenDates(from: String?, to: String?): List<Seance>
    suspend fun getDetailedSeances(): List<SeanceExtendedResponse>
    suspend fun getDetailedSeancesBetweenDates(from: String?, to: String?): List<SeanceExtendedResponse>
    suspend fun getSeancesBetweenDatesByPerformanceId(from: String?, to: String?, id: Int?): List<Seance>
    suspend fun getDetailed(seanceId : Int?): SeanceExtendedResponse?
    suspend fun deleteById(seanceId: Int?)
}