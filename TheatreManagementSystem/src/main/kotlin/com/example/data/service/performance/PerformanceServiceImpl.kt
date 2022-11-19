package com.example.data.service.performance

import com.example.data.model.Performance
import com.example.data.repository.performance.PerformanceRepository
import com.example.data.request.PerformanceRequest
import com.example.exception.ItemNotFoundException
import com.example.exception.ParsingException
import com.example.exception.ValidationException

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
}