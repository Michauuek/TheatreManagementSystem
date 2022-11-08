package com.example.data.db.schemas

import org.jetbrains.exposed.sql.Table

object ReservedSeats : Table("ReservedSeats")
{
    val IdReservation = integer("id_reservation").references(ReservationTable.id)
    val SeatName = varchar("seat_name", 128)

    override val primaryKey = PrimaryKey(arrayOf(IdReservation, SeatName))
}