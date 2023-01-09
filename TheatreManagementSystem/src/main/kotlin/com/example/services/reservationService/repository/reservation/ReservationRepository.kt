package com.example.services.reservationService.repository.reservation

import com.example.db.DatabaseFactory
import com.example.db.extension.toReservation
import com.example.db.schemas.ReservationTable
import com.example.db.model.Reservation
import com.example.db.schemas.ReservedSeatsTable
import com.example.db.schemas.SeanceTable
import com.example.request.reservation.AddReservation
import com.example.request.reservation.AddReservationRequest
import com.example.response.reservation.ReservationData
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.time.LocalDateTime

class ReservationRepository {
    suspend fun add(reservationRequest: AddReservation): Reservation? {
        val currentTime = LocalDateTime.now()

        val reservedSeats = reservationRequest.reservedSeats;

        val statement = DatabaseFactory.dbQuery {
            // check if seats are available
            val areSeatsAvalible = ReservedSeatsTable.select {
                ReservedSeatsTable.seatId inList reservedSeats
            }.empty();

            if(!areSeatsAvalible) {
                return@dbQuery null;
            }

            val out = ReservationTable.insert {
                it[clientName] = reservationRequest.clientName
                it[clientEmail] = reservationRequest.clientEmail
                it[clientPhone] = reservationRequest.clientPhone
                it[date] = currentTime.toLocalDate()
                it[time] = currentTime.toLocalTime()
                it[seanceId] = reservationRequest.seanceId
                it[reservationIPAddress] = reservationRequest.reservationIPAddress
                it[reservationAuthMode] = reservationRequest.reservationAuthMode
            }

            ReservedSeatsTable.batchInsert(reservedSeats) {
                this[ReservedSeatsTable.seatId] = it
                this[ReservedSeatsTable.reservationId] = out[ReservationTable.reservationId]
            }

            out
        }

        return statement?.resultedValues?.first().toReservation()
    }

    suspend fun getAllReservationsForSeance(seanceId: Int): List<ReservationData> {
        val reservations = DatabaseFactory.dbQuery {
            ReservationTable.select {
                ReservationTable.seanceId eq seanceId
            }.mapNotNull {
                it.toReservation() 
            };
        }

        return reservations.map {
            ReservationData(
                seanceId = it.seanceId,
                reservationDate = it.reservationDate,
                reservationTime = it.reservationTime,
                clientName = it.clientName,
                clientEmail = it.clientEmail,
                clientPhone = it.clientPhone,
                reservationIPAddress = it.reservationIPAddress,
                reservationAuthMode = it.reservationAuthMode,
                reservedSeats = it.reservedSeats.stream().map { it.id!! }.toList()
            )
        }
    }

    suspend fun getAllReservedSeatsForSeance(seanceId: Int): List<Int> = DatabaseFactory.dbQuery {
        ReservationTable.join(ReservedSeatsTable, JoinType.INNER, ReservationTable.reservationId, ReservedSeatsTable.reservationId)
            .select {
                ReservationTable.seanceId eq seanceId
            }.mapNotNull {
                it[ReservedSeatsTable.seatId]
            }
    }

    suspend fun getAll(): List<Reservation> {
        return listOf()
    }

    suspend fun deleteById(reservationId: Int) {
        DatabaseFactory.dbQuery {
            ReservationTable.deleteWhere{ ReservationTable.reservationId eq reservationId}
        }
    }
}