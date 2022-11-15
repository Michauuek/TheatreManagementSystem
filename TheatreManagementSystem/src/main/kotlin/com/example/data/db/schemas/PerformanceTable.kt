package com.example.data.db.schemas

import org.jetbrains.exposed.sql.Table


object PerformanceTable: Table("halls") {
    val performanceId = integer("id").autoIncrement()
    val title = varchar("title", 256)
    val description = varchar("description", 1024)
    val photoUrl = varchar("photo_url", 512)
    val castId = integer("id_cast")

    override val primaryKey = PrimaryKey(performanceId, name = "PK_PERF_ID")
}
