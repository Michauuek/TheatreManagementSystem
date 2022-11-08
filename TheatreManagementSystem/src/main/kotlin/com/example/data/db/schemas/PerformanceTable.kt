package com.example.data.db.schemas

import org.jetbrains.exposed.sql.Table


object PerformanceTable: Table("halls") {
    val id          = integer("id").autoIncrement().uniqueIndex()
    val Title       = varchar("title", 1024)
    val Description = varchar("description", 1024)
    val IdCast      = integer("id_cast")

    //override val primaryKey = PrimaryKey(id, name = "PK_PERF_ID")
}