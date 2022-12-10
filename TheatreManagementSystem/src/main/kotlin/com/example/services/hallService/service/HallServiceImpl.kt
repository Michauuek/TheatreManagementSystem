package com.example.services.hallService.service

import com.example.db.model.Hall
import com.example.db.model.Seats
import com.example.services.hallService.repository.HallRepository
import com.example.request.hall.GetSeatsRequest
import com.example.request.hall.HallRequest

class HallServiceImpl(
    private val hallRepository: HallRepository
): HallService {

    override suspend fun add(hallRequest: HallRequest): Hall? {
        return hallRepository.add(hallRequest)
    }

    override suspend fun getAll(): List<Hall> {
        return hallRepository.getAll()
    }

    override suspend fun getAllSeats(getSeatsRequest: GetSeatsRequest): List<Seats>? {
        return hallRepository.getAllSeats(getSeatsRequest)
    }
}