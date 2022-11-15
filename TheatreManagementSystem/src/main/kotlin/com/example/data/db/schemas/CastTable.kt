package com.example.data.db.schemas

import com.example.data.db.schemas.PerformanceTable.autoIncrement
import org.jetbrains.exposed.sql.Table

object CastTable: Table("cast"){
    val castId = integer("cast_id").autoIncrement()
    val actorId = integer("actor_id")
        //.references(AcotorTable.actorId)
    val role = varchar("role", 256)

    override val primaryKey = PrimaryKey(castId, name = "PK_CAST_ID")
}