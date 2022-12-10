package com.example.services.reservationService.repository.reservedSeats

import com.example.db.model.ReservedSeats
import com.example.request.reservation.ReservedSeatsRequest

class ReservedSeatsRepositoryImpl: ReservedSeatsRepository {
    override suspend fun add(reservedSeatsRequest: ReservedSeatsRequest): ReservedSeats? {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Int): ReservedSeats? {
        TODO("Not yet implemented")
    }
}