package com.example.data.db.schemas



import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.time

object SeanceTable: Table("seances") {
    val id = integer("id").autoIncrement()
    val hallId  = varchar("hall_id", 256)
        .references(HallTable.hallName)

    val performanceId = integer("performance_id")
        .references(PerformanceTable.performanceId)

    val seanceDate = date("seance_date")
    val seanceTime = time("seance_time")

    override val primaryKey = PrimaryKey(id, name = "PK_SEANCE_ID")
}