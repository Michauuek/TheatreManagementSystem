package com.example.data.db.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object UserTable : Table("users") {
    val id = integer("id").autoIncrement()
    val email = varchar("email", 256)
    val password = text("password")
    override val primaryKey = PrimaryKey(id)
}