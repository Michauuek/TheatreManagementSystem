package com.example.services.reservationService.repository.reservation

import com.example.db.DatabaseFactory
import com.example.db.extension.toReservation
import com.example.db.schemas.ReservationTable
import com.example.db.model.Reservation
import com.example.request.reservation.AddReservationRequest
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.time.LocalDateTime

class ReservationRepository {
    suspend fun add(reservationRequest: AddReservationRequest): Reservation? {
        val currentTime = LocalDateTime.now()

        val statement = DatabaseFactory.dbQuery {
            ReservationTable.insert {
                it[clientName] = reservationRequest.clientName
                it[clientEmail] = reservationRequest.clientEmail
                it[date] = currentTime.toLocalDate()
                it[time] = currentTime.toLocalTime()
                it[seanceId] = reservationRequest.seanceId
            }
        }

        return statement.resultedValues?.first().toReservation()
    }

    suspend fun getAllReservationsForSeance(seanceId: Int): List<Reservation> {
        val statement = DatabaseFactory.dbQuery {
            ReservationTable.select {
                ReservationTable.seanceId eq seanceId
            }
        }
        return statement.mapNotNull { it.toReservation() }
    }

    suspend fun getAll(): List<Reservation> {
        return listOf()
    }


}