package com.example.data.db.schemas

import com.example.data.db.schemas.ScheduleTable.autoIncrement
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.time

object HallTable: Table("halls") {
    val HallName = varchar("hall_name", 256).uniqueIndex()
    val SeatsLayout = varchar("seats_layout", 1024)
    val BackgroundPath = varchar("background_path", 256)

    //override val primaryKey = PrimaryKey(HallName, name = "PK_HALL_ID")
}