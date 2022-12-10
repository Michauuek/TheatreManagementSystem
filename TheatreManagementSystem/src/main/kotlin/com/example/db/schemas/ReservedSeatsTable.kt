package com.example.db.schemas

import org.jetbrains.exposed.sql.Table

object ReservedSeatsTable : Table("ReservedSeats") {
    val reservationId = integer("id_reservation").references(ReservationTable.reservationId)
    val seatId = integer("seat_id").references(SeatsTable.seatId)

    override val primaryKey = PrimaryKey(reservationId, seatId)
}