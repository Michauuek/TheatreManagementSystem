package com.example.services.reservationService.repository.reservedSeats

import com.example.db.DatabaseFactory
import com.example.db.schemas.ReservationTable
import com.example.db.schemas.ReservedSeatsTable
import com.example.request.reservation.AddReservedSeats
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class ReservedSeatsRepositoryImpl: ReservedSeatsRepository {
    override suspend fun add(reservedSeatsRequest:AddReservedSeats): AddReservedSeats? {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Int): AddReservedSeats? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteByReservationId(reservationId: Int) {
        DatabaseFactory.dbQuery {
            ReservedSeatsTable.deleteWhere{ ReservedSeatsTable.reservationId eq reservationId}
        }
    }
}