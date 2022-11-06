package com.example.data.db.extension

import com.example.data.db.schemas.SeanceTable
import com.example.data.db.schemas.UserTable
import com.example.data.model.Seance
import com.example.data.model.User
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow?.toSeance(): Seance? {
    return if (this == null) null
    else Seance(
        id = this[SeanceTable.id],
        title = this[SeanceTable.title],
        genre = this[SeanceTable.genre],
        director = this[SeanceTable.director],
        duration = this[SeanceTable.duration]
    )
}

fun ResultRow?.toUser(): User? {
    return if (this == null) null
    else User(
        id = this[UserTable.id],
        email = this[UserTable.email],
        password = this[UserTable.password]
    )
}
