package com.example.db.schemas

import org.jetbrains.exposed.sql.Table


object PerformanceTable: Table("performance") {

    val performanceId = integer("id").autoIncrement()
    val title = varchar("title", 256)
    val description = varchar("description", 1024)
    val imageUrl = varchar("image_url", 1024)

    override val primaryKey = PrimaryKey(performanceId)
}
