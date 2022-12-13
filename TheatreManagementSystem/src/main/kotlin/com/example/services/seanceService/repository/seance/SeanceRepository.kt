package com.example.services.seanceService.repository.seance


import com.example.db.model.Seance
import com.example.request.seance.SeanceRequest
import com.example.response.seance.SeanceExtendedResponse
import java.time.LocalDate


interface SeanceRepository {
    suspend fun addSeance(seanceRequest: SeanceRequest): Seance?
    suspend fun getAllSeances(): List<Seance>

    suspend fun getDetailedSeances(): List<SeanceExtendedResponse>
    suspend fun getSeancesBetweenDates(fromDate: LocalDate, toDate: LocalDate): List<Seance>
    suspend fun getDetailedSeancesBetweenDates(fromDate: LocalDate,toDate: LocalDate): List<SeanceExtendedResponse>
}