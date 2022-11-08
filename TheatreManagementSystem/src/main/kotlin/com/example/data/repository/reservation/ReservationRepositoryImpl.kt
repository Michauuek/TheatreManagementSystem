package com.example.data.repository.reservation

import com.example.data.db.DatabaseFactory
import com.example.data.db.extension.toReservation
import com.example.data.db.schemas.ReservationTable
import com.example.data.model.Reservation
import com.example.data.request.ReservationRequest
import org.jetbrains.exposed.sql.insert
import java.time.LocalDateTime

class ReservationRepositoryImpl : ReservationRepository
{
    override suspend fun add(reservationRequest: ReservationRequest): Reservation? {
        var reservationTime = LocalDateTime.now();

        var statement = DatabaseFactory.dbQuery {
            ReservationTable.insert {
                it[ClientName] = reservationRequest.ClientName
                it[ClientEmail] = reservationRequest.ClientEmail
                it[ReservationDate] = reservationTime.toLocalDate()
                it[ReservationTime] = reservationTime.toLocalTime()
            }
        }

        return statement.resultedValues?.first().toReservation()
    }

    override suspend fun getAll(): List<Reservation> {
        TODO("Not yet implemented")
        return listOf<Reservation>()
    }
}