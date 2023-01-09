package com.example.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.time

/**
 * ## Hall table
 *
 * This table is used to store halls. Each hall has seats in it.
 * For menagement purposes there are scripts that can be used to add halls with seats to the database.
 * Hall size is ambiguous and can be determined by the seats in it. (max x and max y)
 *
 * @see com.example.db.schemas.SeatsTable
 * @see com.example.services.hallService
 */
object HallTable: Table("halls") {
    /**
     * ## Unique name of Hall
     * ## PRIMARY KEY
     * *Example* - `Sala Numer 1`
     */
    val hallName = varchar("hall_name", 256)

    /**
     * ## Path to backgroud image used in background
     * Path to endpoint in `hallService`
     *
     * @see com.example.services.hallService
     */
    val backgroundPath = varchar("background_path", 256)
    /**
     * ## Unique name of Hall
     * ## PRIMARY KEY
     * *Example* - `Sala Numer 1`
     */
    val seatScale = double("seat_scale")

    override val primaryKey = PrimaryKey(hallName)
}