package com.example.services.reservationService.repository.reservedSeats

import com.example.db.model.Reservation
import com.example.db.model.ReservedSeats
import com.example.request.reservation.ReservationRequest
import com.example.request.reservation.ReservedSeatsRequest

interface ReservedSeatsRepository {
    suspend fun add(reservedSeatsRequest: ReservedSeatsRequest): ReservedSeats?
    suspend fun getById(id: Int): ReservedSeats?
}