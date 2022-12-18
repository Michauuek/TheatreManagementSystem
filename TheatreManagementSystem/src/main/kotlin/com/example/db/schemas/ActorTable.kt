package com.example.db.schemas

import com.example.db.schemas.CastTable.autoIncrement
import org.jetbrains.exposed.sql.Table

/**
 * ## Table for storing actors
 *
 * This table is used to store actors in the database.
 * It is used in the application to show actors in the cast of the performance.
 *
 * @see com.example.db.schemas.CastTable
 * @see com.example.db.schemas.PerformanceTable
 * @see com.example.services.seanceService
 */
object ActorTable: Table("actors"){
    /**
     * ## Actor id
     * ## PRIMARY_KEY
     * ## AUTO_INCREMENT
     */
    val actorId = integer("actor_id").autoIncrement()

    /**
     * ## Actor name
     * * Example: `John`
     */
    val name = varchar("name", 64)

    /**
     * ## Actor surname
     * * Example: `Doe`
     */
    val surname = varchar("surname", 64)

    /**
     * ## Actor photo url
     *
     * @see com.example.services.seanceService
     */
    val photoUrl = varchar("photo_url", 1024)

    override val primaryKey = PrimaryKey(actorId)
}