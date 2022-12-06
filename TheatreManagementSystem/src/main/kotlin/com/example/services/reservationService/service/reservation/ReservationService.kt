package com.example.services.reservationService.service.reservation

import com.example.db.model.Reservation
import com.example.request.reservation.ReservationRequest



// tutaj będzie sporo roboty: trzeba
// * Sprawdzić czy
interface ReservationService
{
    suspend fun add(reservationRequest: ReservationRequest): Reservation?
    suspend fun getAll(): List<Reservation>
}