package com.example.data.db.schemas


import org.jetbrains.exposed.sql.ForeignKeyConstraint
import org.jetbrains.exposed.sql.Table

object SeanceTable: Table("seances") {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 256)
    val genre = varchar("genre", 256)
    val director = varchar("director", 256)
    val duration = integer("duration")
    val price = float("price")
    /*val scheduleId = (integer("schedule_id") references ScheduleTable.id).nullable()
    val hallId = (integer("hall_id") references HallTable.id).nullable()*/

    override val primaryKey = PrimaryKey(id, name = "PK_SEANCE_ID")
}