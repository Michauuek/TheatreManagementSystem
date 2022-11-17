package com.example.data.service.seance

import com.example.data.model.Seance
import com.example.data.repository.seance.SeanceRepository
import com.example.data.request.SeanceRequest
import com.example.data.response.SeanceExtendedResponse
import com.example.exception.ParsingException
import io.ktor.utils.io.errors.*
import java.time.LocalDate


class SeanceServiceImpl(
    private val seanceRepository: SeanceRepository
): SeanceService {

    override suspend fun add(seanceRequest: SeanceRequest): Seance {
        return seanceRepository.addSeance(seanceRequest) ?: throw IOException("User could not be added.")
    }

    override suspend fun getAll(): List<Seance> =
        seanceRepository.getAllSeances()

    override suspend fun getSeancesBetweenDates(from: String?, to: String?): List<Seance>{
        val fromDate = LocalDate.parse(from)
        val toDate = LocalDate.parse(to)
        if(fromDate >toDate)
            throw ParsingException("Wrong date range!")

        return seanceRepository.getSeancesBetweenDates(fromDate,toDate)
    }

    override suspend fun getDetailedSeances(): List<SeanceExtendedResponse>{
        return seanceRepository.getDetailedSeances()
    }

}