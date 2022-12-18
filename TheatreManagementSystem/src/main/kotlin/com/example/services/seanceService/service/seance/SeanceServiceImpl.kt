package com.example.services.seanceService.service.seance

import com.example.db.model.Seance
import com.example.exception.ParsingException
import com.example.exception.ValidationException
import com.example.request.seance.SeanceRequest
import com.example.response.seance.SeanceExtendedResponse
import com.example.services.seanceService.repository.seance.SeanceRepository
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

    override suspend fun getDetailedSeancesBetweenDates(from: String?, to: String?): List<SeanceExtendedResponse>{
        val fromDate = LocalDate.parse(from)
        val toDate = LocalDate.parse(to)
        if(fromDate >toDate)
            throw ParsingException("Wrong date range!")

        return seanceRepository.getDetailedSeancesBetweenDates(fromDate,toDate)


    }

    override suspend fun getDetailed(seanceId : Int?): SeanceExtendedResponse?{

        if(seanceId == null || seanceId < 0)
            throw ValidationException("Wrong seanceId!")
        return seanceRepository.getDetailed(seanceId)
    }

    override suspend fun getSeancesBetweenDatesByPerformanceId(from: String?, to: String?, id: Int?): List<Seance>{
        val fromDate = LocalDate.parse(from)
        val toDate = LocalDate.parse(to)
        if(fromDate >toDate)
            throw ParsingException("Wrong date range!")
        if(id == null || id < 0)
            throw ValidationException("Wrong performanceId!")

        return seanceRepository.getSeancesBetweenDatesByPerformanceId(fromDate,toDate, id)
    }
}