package com.example.data.service.reservation

import com.example.data.model.Reservation
import com.example.data.repository.reservation.ReservationRepository
import com.example.data.request.ReservationRequest

class ReservationServiceImpl(
    private val reservationRepository: ReservationRepository
): ReservationService {
    override suspend fun add(reservationRequest: ReservationRequest): Reservation? {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): List<Reservation> {
        TODO("Not yet implemented")
    }
}