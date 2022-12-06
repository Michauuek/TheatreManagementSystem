package com.example.db.schemas

import org.jetbrains.exposed.sql.Table

object SeatsTable: Table("seats") {
    val seatID   = integer("seat_id").autoIncrement()
    val hallName = varchar("hall_name",256)
        //.references(HallTable.hallName)
    val seatName = varchar("seat_name", 256)
    val posX     = float("posX")
    val posY     = float("posY")

    override val primaryKey = PrimaryKey(SeanceTable.id)
}