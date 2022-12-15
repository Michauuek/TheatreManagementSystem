package com.example.services.reservationService.repository.reservedSeats

import com.example.request.reservation.AddReservedSeats

class ReservedSeatsRepositoryImpl: ReservedSeatsRepository {
    override suspend fun add(reservedSeatsRequest:AddReservedSeats): AddReservedSeats? {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Int): AddReservedSeats? {
        TODO("Not yet implemented")
    }
}