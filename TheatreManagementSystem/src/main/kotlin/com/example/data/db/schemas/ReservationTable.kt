package com.example.data.db.schemas

import com.example.data.db.schemas.ScheduleTable.autoIncrement
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.time

object ReservationTable: Table("reservation") {
    val id              = integer("id").autoIncrement().uniqueIndex()
    val ReservationDate = date("reservation_date")
    val ReservationTime = time("seance_time")
    val ClientName      = varchar("client_name", 128)
    val ClientEmail     = varchar("client_email", 128)
    //TODO SeanceID
}