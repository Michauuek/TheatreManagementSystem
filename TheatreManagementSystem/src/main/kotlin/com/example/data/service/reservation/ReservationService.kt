package com.example.data.service.reservation

import com.example.data.model.Reservation
import com.example.data.request.ReservationRequest



// tutaj będzie sporo roboty: trzeba
// * Sprawdzić czy
interface ReservationService
{
    suspend fun add(reservationRequest: ReservationRequest): Reservation?
    suspend fun getAll(): List<Reservation>
}