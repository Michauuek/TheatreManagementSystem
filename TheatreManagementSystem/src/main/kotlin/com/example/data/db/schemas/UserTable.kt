package com.example.data.db.schemas

import com.example.data.db.schemas.ActorTable.long
import com.example.data.db.schemas.ActorTable.reference
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object UserTable : Table("users") {
    val id = integer("id").autoIncrement()
    val email = varchar("email", 256)
    val password = text("password")
    override val primaryKey = PrimaryKey(id)
}

object Users: IntIdTable() {
    val name = varchar("name", 50)
}
object StarWarsFilm: IntIdTable() {
    val name = varchar("name", 50)
}
object UserRatings: IntIdTable() {
    val value = long("value")
    val film = reference("film", StarWarsFilm)
    val user = reference("user", Users)
}