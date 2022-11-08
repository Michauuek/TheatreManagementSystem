package com.example.data.service.reservation

import com.example.data.model.Reservation
import com.example.data.request.ReservationRequest

interface ReservationService
{
    suspend fun add(reservationRequest: ReservationRequest): Reservation?

    suspend fun getAll(): List<Reservation>
}