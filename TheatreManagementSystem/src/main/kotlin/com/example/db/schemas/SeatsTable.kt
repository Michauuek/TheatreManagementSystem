package com.example.db.schemas

import org.jetbrains.exposed.sql.Table

object SeatsTable: Table("seats") {
    val seatId = integer("seat_id").autoIncrement()
    val hallName = varchar("hall_name",256).references(HallTable.hallName)
    val seatName = varchar("seat_name", 256)
    val posX = float("pos_x")
    val posY = float("pos_y")

    override val primaryKey = PrimaryKey(SeanceTable.id)
}