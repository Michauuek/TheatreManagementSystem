package com.example.services.reservationService.repository.reservation

import com.example.db.DatabaseFactory
import com.example.db.extension.toReservation
import com.example.db.schemas.ReservationTable
import com.example.db.model.Reservation
import com.example.request.reservation.ReservationRequest
import org.jetbrains.exposed.sql.insert
import java.time.LocalDateTime

class ReservationRepositoryImpl : ReservationRepository
{
    override suspend fun add(reservationRequest: ReservationRequest): Reservation? {
        val currentTime = LocalDateTime.now()

        val statement = DatabaseFactory.dbQuery {
            ReservationTable.insert {
                it[clientName] = reservationRequest.clientName
                it[clientEmail] = reservationRequest.clientEmail
                it[reservationDate] = currentTime.toLocalDate()
                it[reservationTime] = currentTime.toLocalTime()
            }
        }

        return statement.resultedValues?.first().toReservation()
    }

    override suspend fun getAll(): List<Reservation> {
        // lista Reservation

        TODO("Not yet implemented")
        return listOf<Reservation>()
    }
}