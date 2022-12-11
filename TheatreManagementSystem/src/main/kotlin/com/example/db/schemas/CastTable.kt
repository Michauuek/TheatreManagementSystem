package com.example.db.schemas

import com.example.db.schemas.PerformanceTable.autoIncrement
import org.jetbrains.exposed.sql.Table

object CastTable: Table("cast"){
    val performanceId = integer("performance_id").references(PerformanceTable.performanceId)
    val actorId = integer("actor_id").references(ActorTable.actorId)
    val role = varchar("role", 256)

    override val primaryKey = PrimaryKey(actorId, performanceId)
}