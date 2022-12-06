package com.example.db.schemas


import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.time

object ReservationTable: Table("reservation") {
    val reservationId = integer("id").autoIncrement()
    val reservationDate = date("reservation_date")
    val reservationTime = time("reservation_time")
    val clientName = varchar("client_name", 128)
    val clientEmail = varchar("client_email", 128)
    val idSeance = integer("id_seance")

    override val primaryKey = PrimaryKey(reservationId)
}