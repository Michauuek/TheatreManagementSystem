package com.example.db.schemas

import org.jetbrains.exposed.sql.Table

/**
 * ## Table for storing seats in hall
 *
 * This table is used to store seats in hall. Each seat has unique id and is related to hall.
 *
 *
 * @see com.example.db.schemas.HallTable
 * @see com.example.services.seanceService
 * @see com.example.services.reservationService
 * @see com.example.services.hallService
 * @see com.example.services.seatService
 */
object SeatsTable: Table("seats") {
    /**
     * ## Seat ID
     * ## PRIMARY_KEY
     * ## AUTO_INCREMENT
     *
     * Used to identify seat in the database.
     */
    val seatId = integer("seat_id").autoIncrement()

    /**
     * ## Name of the hall in which seat is located
     * ## REFERENCES `HallTable.hallName`
     *
     * @see com.example.db.schemas.HallTable.hallName
     */
    val hallName = varchar("hall_name",256).references(HallTable.hallName)

    /**
     * ## Seat Name
     *
     * Used to identify seat in the application. it is displayed in the application.
     * Example `A1`
     */
    val seatName = varchar("seat_name", 256)

    /**
     * ## Pos X of the seat
     *
     * it is position of the seat in the hall, it is used to display seat in the application.
     * (unit: meters)
     */
    val posX = float("pos_x")

    /**
     * ## Pos Y of the seat
     *
     * it is position of the seat in the hall, it is used to display seat in the application.
     * (unit: meters)
     */
    val posY = float("pos_y")

    override val primaryKey = PrimaryKey(seatId)
}