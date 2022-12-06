package com.example.services.reservationService.service.reservation

import com.example.db.model.Reservation
import com.example.services.reservationService.repository.reservation.ReservationRepository
import com.example.request.reservation.ReservationRequest

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