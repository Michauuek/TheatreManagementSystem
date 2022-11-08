package com.example.data.repository.reservation

import com.example.data.model.Reservation
import com.example.data.request.ReservationRequest

interface ReservationRepository
{
    suspend fun add(reservationRequest: ReservationRequest): Reservation?
    suspend fun getAll(): List<Reservation>
}