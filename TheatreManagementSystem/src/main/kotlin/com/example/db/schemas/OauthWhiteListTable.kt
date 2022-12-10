package com.example.db.schemas

import org.jetbrains.exposed.sql.Table

object OauthWhiteListTable : Table("auth_white_list") {
    val id = integer("id").autoIncrement()
    val email = varchar("email", 256)
    val role = varchar("role",256)
    override val primaryKey = PrimaryKey(id)
}
