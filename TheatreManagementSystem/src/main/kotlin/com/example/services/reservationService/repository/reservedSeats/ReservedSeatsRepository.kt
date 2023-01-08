package com.example.services.reservationService.repository.reservedSeats

import com.example.request.reservation.AddReservedSeats


interface ReservedSeatsRepository {
    suspend fun add(reservedSeatsRequest: AddReservedSeats): AddReservedSeats?
    suspend fun getById(id: Int): AddReservedSeats?
    suspend fun deleteByReservationId(reservationId: Int)
}