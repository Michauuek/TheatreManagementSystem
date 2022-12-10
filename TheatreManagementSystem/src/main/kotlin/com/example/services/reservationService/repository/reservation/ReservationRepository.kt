package com.example.services.reservationService.repository.reservation

import com.example.db.model.Reservation
import com.example.request.reservation.ReservationRequest

interface ReservationRepository {
    suspend fun add(reservationRequest: ReservationRequest): Reservation?
    suspend fun getAll(): List<Reservation>
}