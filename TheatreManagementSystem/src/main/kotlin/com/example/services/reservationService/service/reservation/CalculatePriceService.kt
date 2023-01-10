package com.example.services.reservationService.service.reservation;

import com.example.auth.fromJson
import com.example.config.getHttpClient
import com.example.response.seance.SeanceExtendedResponse
import io.ktor.client.*
import io.ktor.client.request.*

class CalculatePriceService(
    private val httpClient: HttpClient
) {
    suspend fun calculatePrice(seanceId: Int, seats: List<Int>): Double
    {
        val numberOfSeats = seats.size;

        val seancePrice = httpClient
            .get("http://localhost:8084/seance/get-detailed?id=$seanceId")
            .fromJson<SeanceExtendedResponse>()
            .price

        return seancePrice * numberOfSeats
    }
}
