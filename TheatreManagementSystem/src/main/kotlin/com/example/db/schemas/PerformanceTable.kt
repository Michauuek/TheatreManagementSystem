package com.example.db.schemas

import org.jetbrains.exposed.sql.Table


/**
 * ## Table for Perfroamnces
 *
 * This table represents performances in the database. it is used to store information about performances.
 * and it is related to other tables - `CastTable` and `SeanceTable`. Performance is an entity
 * that is shown in main page or in the seance page.
 *
 * @see com.example.db.schemas.CastTable
 * @see com.example.db.schemas.SeanceTable
 * @see com.example.services.seanceService
 */
object PerformanceTable: Table("performance") {
    /**
     * ## Unique ID of the performance
     * ## PRIMARY KEY
     * ## AUTO_INCREMENT
     *
     * Used to identify the performance in the database and in the application itself
     */
    val performanceId = integer("id").autoIncrement()

    /**
     * ## Tile of the performance
     * Title of the performance, used in the application to performance name
     */
    val title = varchar("title", 256)

    /**
     * ## Description of performance
     * Description of the performance, used in the application to show the description of the performance
     */
    val description = varchar("description", 1024)

    /**
     * ## image url of the performance
     * Image url of the performance, used in the application to show the image of the performance,
     * probably path to the performance image in `seance` service
     *
     * @see com.example.services.seanceService
     */
    val imageUrl = varchar("image_url", 1024)
    /**
     * ## Length of the performance
     * Length of the performance in minutes
     */
    val length = integer("length")

    override val primaryKey = PrimaryKey(performanceId)
}
