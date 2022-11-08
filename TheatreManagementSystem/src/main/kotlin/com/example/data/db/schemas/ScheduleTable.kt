package com.example.data.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.time

//TODO Do wyjebania bo to to samo co Reservation w nowym modelu?
object ScheduleTable: Table("schedules") {
    val id         = integer("id").autoIncrement().uniqueIndex()
    val seanceDate = date("seance_date")
    val seanceTime = time("seance_time")

    //override val primaryKey = PrimaryKey(id, name = "PK_SCHEDULE_ID")
}