package com.example.data.repository.seance


import com.example.data.model.Seance
import com.example.data.request.SeanceRequest
import com.example.data.response.SeanceExtendedResponse
import java.time.LocalDate


interface SeanceRepository {
    suspend fun addSeance(seanceRequest: SeanceRequest): Seance?
    suspend fun getAllSeances(): List<Seance>

    suspend fun getDetailedSeances(): List<SeanceExtendedResponse>
    suspend fun getSeancesBetweenDates(fromDate: LocalDate, toDate: LocalDate): List<Seance>
}