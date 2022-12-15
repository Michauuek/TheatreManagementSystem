package com.example.services.hallService.service

import com.example.db.model.Hall
import com.example.request.hall.GetSeatsRequest
import com.example.request.hall.HallAddRequest
import com.example.response.hall.SeatsResponse
import com.example.services.hallService.repository.HallRepository

class HallService(
    private val hallRepository: HallRepository
) {
    suspend fun add(hallAddRequest: HallAddRequest): Hall? {
        return hallRepository.add(hallAddRequest)
    }

    suspend fun getByName(hallName: String): Hall? {
        return hallRepository.getHall(hallName)
    }

    suspend fun getAll(): List<Hall> {
        return hallRepository.getAll()
    }
    suspend fun getAllSeats(getSeatsRequest: String): SeatsResponse {
        return hallRepository.getSeatsFromHall(getSeatsRequest)
    }
}