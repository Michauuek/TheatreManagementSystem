package com.example.data.db.schemas

import org.jetbrains.exposed.sql.Table


object PerformanceTable: Table("performance") {
    /**
     * ## Unique ID of the performance
     * ## PRIMARY KEY
     */
    val performanceId = integer("id").autoIncrement()

    /**
     * ## Tile of the performance
     * Displayed on main site
     */
    val title = varchar("title", 256)

    /**
     * ## Description of performance
     */
    val description = varchar("description", 1024)

    /**
     * ## ID of cast that plays in this performance
     * ## REFERENCES `Cast.CastID`
     */
    val castId = integer("id_cast")

    override val primaryKey = PrimaryKey(performanceId, name = "PK_PERF_ID")
}
