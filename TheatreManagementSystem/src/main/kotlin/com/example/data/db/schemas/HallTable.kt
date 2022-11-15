package com.example.data.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.time

object HallTable: Table("halls") {
    val hallName = varchar("hall_id", 256).autoIncrement()
    val seatsLayout = varchar("seats_layout", 1024)
    val backgroundPath = varchar("background_path", 256)

    override val primaryKey = PrimaryKey(hallName)
}