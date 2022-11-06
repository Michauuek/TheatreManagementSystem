package com.example.data.db.schemas

import org.jetbrains.exposed.sql.Table

object SeanceTable: Table("seances") {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 256)
    val genre = varchar("genre", 256)
    val director = varchar("director", 256)
    val duration = integer("duration")

    override val primaryKey = PrimaryKey(id)
}