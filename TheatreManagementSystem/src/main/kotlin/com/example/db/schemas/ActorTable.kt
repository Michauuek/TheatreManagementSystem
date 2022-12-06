package com.example.db.schemas

import com.example.db.schemas.CastTable.autoIncrement
import org.jetbrains.exposed.sql.Table

object ActorTable: Table("actors"){
    val actorId = integer("actor_id").autoIncrement()
    val name = varchar("name", 64)
    val surname = varchar("surname", 64)

    override val primaryKey = PrimaryKey(actorId)
}