package com.example.data.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.time

object ScheduleTable: Table("schedules") {
    val id = integer("id").autoIncrement()
    val seanceDate = date("seance_date")
    val seanceTime = time("seance_time")

    override val primaryKey = PrimaryKey(id, name = "PK_SCHEDULE_ID")
}