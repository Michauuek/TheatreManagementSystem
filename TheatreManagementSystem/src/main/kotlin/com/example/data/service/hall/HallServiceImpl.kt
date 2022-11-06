package com.example.data.service.hall

import com.example.data.model.Hall
import com.example.data.repository.hall.HallRepository
import com.example.data.request.HallRequest

class HallServiceImpl(
    private val hallRepository: HallRepository
): HallService {

    override suspend fun add(hallRequest: HallRequest): Hall? {
        return hallRepository.add(hallRequest)
    }

    override suspend fun getAll(): List<Hall> {
        return hallRepository.getAll()
    }
}