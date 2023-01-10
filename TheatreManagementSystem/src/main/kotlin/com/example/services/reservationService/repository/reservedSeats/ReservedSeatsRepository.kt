package com.example.services.reservationService.repository.reservedSeats

import com.example.db.DatabaseFactory
import com.example.db.schemas.ReservedSeatsTable
import com.example.request.reservation.AddReservedSeats
import org.jetbrains.exposed.sql.deleteWhere

class ReservedSeatsRepository {
    suspend fun add(reservedSeatsRequest:AddReservedSeats): AddReservedSeats? {
        TODO("Not yet implemented")
    }

    suspend fun getById(id: Int): AddReservedSeats? {
        TODO("Not yet implemented")
    }

    suspend fun deleteByReservationId(reservationId: Int) {
        DatabaseFactory.dbQuery {
            ReservedSeatsTable.deleteWhere{ ReservedSeatsTable.reservationId eq reservationId}
        }
    }
}