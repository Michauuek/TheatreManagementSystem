package com.example.reservationService.service.hall.performance

import com.example.db.model.Performance
import com.example.exception.ItemNotFoundException
import com.example.exception.ParsingException
import com.example.exception.ValidationException
import com.example.request.seance.PerformanceRequest
import com.example.response.seance.PerformancePaginatedResponse
import com.example.response.seance.SeanceExtendedResponse
import com.example.routes.reservationService.service.performance.PerformanceService
import com.example.services.seanceService.repository.performance.PerformanceRepository

class PerformanceServiceImpl(
    private val performanceRepository: PerformanceRepository
): PerformanceService {

    override suspend fun add(performanceRequest: PerformanceRequest): Performance? {
        /*if(castRepository.getById(performanceRequest.castId) == null)
            throw ItemNotFoundException("Cast with provided id not found.")*/
        if(performanceRepository.getByName(performanceRequest.title) != null)
            throw ParsingException("Performance already exists.")

        return performanceRepository.add(performanceRequest)
    }

    override suspend fun getById(id: Int): Performance? {
        if(performanceRepository.getById(id) == null)
            throw ItemNotFoundException("Performance with provided id not found.")
        return performanceRepository.getById(id)
    }

    override suspend fun getByTitle(title: String): Performance? {
        if(title.isEmpty())
            throw ValidationException("Title cannot be empty.")
        if(performanceRepository.getByName(title) == null)
            throw ItemNotFoundException("Performance with provided title not found.")
        return performanceRepository.getByName(title)
    }

    override suspend fun getAll(): List<Performance> {
        return performanceRepository.getAll().ifEmpty { emptyList() }
    }

    override suspend fun getPage(page: Int?, size: Int?): PerformancePaginatedResponse {
        val pageNumber = page ?: 1
        val pageSize = size ?: 2
        return PerformancePaginatedResponse(
            performances = performanceRepository.getPage(pageNumber, pageSize),
            pageSize = pageSize,
            currentPage = pageNumber,
            totalElements = performanceRepository.getAll().size
        )
    }


}