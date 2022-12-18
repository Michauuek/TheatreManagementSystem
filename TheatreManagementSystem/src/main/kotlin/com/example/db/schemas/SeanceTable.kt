package com.example.db.schemas


import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.time

/**
 * ## Table for seances
 *
 * This table is used to store seances in the database. Seance is a performance in a given time and in given hall.
 * People can reserve seats for seances.
 *
 * @see com.example.db.schemas.PerformanceTable
 * @see com.example.db.schemas.ReservedSeatsTable
 * @see com.example.services.seanceService
 * @see com.example.services.reservationService
 * @see com.example.services.hallService
 *
 */
object SeanceTable: Table("seances") {
    /**
     * ## Seance ID
     * ## PRIMARY KEY
     * ## AUTO_INCREMENT
     *
     * ID used to identify seance in the database and in the application itself
     */
    val id = integer("id").autoIncrement()

    /**
     * ## HallName
     * ## REFERENCES `HallTable.id`
     * Name of the hall in which seance is held, it is used to identify hall in the application
     *
     * @see com.example.db.schemas.HallTable.hallName
     */
    val hallName = varchar("hall_id", 256).references(HallTable.hallName)

    /**
     * ## Performance ID
     * ## REFERENCES `PerformanceTable.performanceId`
     * ID of the performance that is held in this seance, it is used to identify performance in the application
     *
     * @see com.example.db.schemas.PerformanceTable.performanceId
     */
    val performanceId = integer("performance_id").references(PerformanceTable.performanceId)

    /**
     * ## Seance date
     * Date in which seance is held example `2020-12-31`.
     */
    val seanceDate = date("seance_date")

    /**
     * ## Seance time
     * Time in which seance is held example `12:00:00`
     */
    val seanceTime = time("seance_time")

    override val primaryKey = PrimaryKey(id)
}