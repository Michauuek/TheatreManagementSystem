package com.example.data.db.schemas

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table


object PerformanceTable: Table("performance") {
    val performanceId = integer("performance_id").autoIncrement()
    val title = varchar("title", 256)
    val description = varchar("description", 1024)
    val castId = integer("id_cast")

    override val primaryKey = PrimaryKey(performanceId)
}