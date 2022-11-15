package com.example.data.db.schemas

import org.jetbrains.exposed.sql.Table

object ReservedSeats : Table("ReservedSeats") {
    val reservationId = integer("id_reservation")
        .references(ReservationTable.reservationId)
    val seatName = varchar("seat_name", 128)

    override val primaryKey = PrimaryKey(arrayOf(reservationId, seatName))
}