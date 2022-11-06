package com.example.data.db.schemas

import com.example.data.db.schemas.ScheduleTable.autoIncrement
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.time

object HallTable: Table("halls") {
    val id = integer("id").autoIncrement()
    val number = varchar("number", 256)
    val initialSeatsAmount = integer("initial_seats_amount")
    var availableSeatsAmount = integer("available_seats_amount")

    override val primaryKey = PrimaryKey(id, name = "PK_HALL_ID")
}