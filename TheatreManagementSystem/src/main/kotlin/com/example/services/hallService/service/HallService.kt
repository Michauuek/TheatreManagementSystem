package com.example.services.hallService.service

import com.example.auth.fromJson
import com.example.db.model.Hall
import com.example.request.hall.GetSeatsRequest
import com.example.request.hall.HallAddRequest
import com.example.response.hall.SeatsResponse
import com.example.response.hall.SeatsWithStateResponse
import com.example.response.seance.SeanceExtendedResponse
import com.example.services.hallService.repository.HallRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class HallService(
    private val hallRepository: HallRepository,
    private val httpClient: HttpClient,
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

    suspend fun getByNameWithSeats(seanceId: Int): SeatsWithStateResponse {
        // get hall name
        val hallName = httpClient
            .get("http://localhost:8084/seance/get-detailed?id=$seanceId")
            .fromJson<SeanceExtendedResponse>()
            .hallName

        val seats = hallRepository.getSeatsFromHall(hallName)

        val reserved = httpClient.get("http://localhost:8083/reservation/all-reserved-seats/${seanceId}");

        return SeatsWithStateResponse(seats, reserved.fromJson())
    }
}