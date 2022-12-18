package com.example.db.schemas

import com.example.db.schemas.PerformanceTable.autoIncrement
import org.jetbrains.exposed.sql.Table

/**
 * ## Table for cast of the performance
 *
 * This table is used to store cast of the performance in the database.
 *
 * @see com.example.db.schemas.ActorTable
 * @see com.example.db.schemas.PerformanceTable
 * @see com.example.services.seanceService
 */
object CastTable: Table("cast"){
    /**
     * ## Performance id
     * ## REFERENCES PerformanceTable.performanceId
     * ## (PART OF) PRIMARY_KEY
     * @see com.example.db.schemas.PerformanceTable.performanceId
     *
     * Used to identify performance that this cast is related to.
     */
    val performanceId = integer("performance_id").references(PerformanceTable.performanceId)

    /**
     * ## Cast id
     * ## REFERENCES ActorTable.actorId
     * @see com.example.db.schemas.ActorTable.actorId
     *
     * Used to identify actor that is **in** this cast.
     */
    val actorId = integer("actor_id").references(ActorTable.actorId)

    /**
     * ## Cast role
     *
     * Used to identify role of the actor in this cast. Displayed in the application.
     */
    val role = varchar("role", 256)

    override val primaryKey = PrimaryKey(actorId, performanceId)
}