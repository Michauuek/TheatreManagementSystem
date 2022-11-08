package com.example.data.db.schemas


import org.jetbrains.exposed.sql.ForeignKeyConstraint
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.time

object SeanceTable: Table("seances") {
    val id            = integer("id").autoIncrement().uniqueIndex()
    val HallId        = varchar("hall_id", 256)
    val PerformanceId = integer("performance_id")
    val seanceDate    = date("seance_date")
    val seanceTime    = time("seance_time")

    //override val primaryKey = PrimaryKey(id, name = "PK_SEANCE_ID")
}