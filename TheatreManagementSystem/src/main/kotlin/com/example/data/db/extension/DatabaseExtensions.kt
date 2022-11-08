package com.example.data.db.extension

import com.example.data.db.schemas.HallTable
import com.example.data.db.schemas.ScheduleTable
import com.example.data.db.schemas.SeanceTable
import com.example.data.db.schemas.UserTable
import com.example.data.model.Hall
import com.example.data.model.Schedule
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
        duration = this[SeanceTable.duration],
        price = this[SeanceTable.price]
        /*scheduleId = this[SeanceTable.scheduleId]!!,
        hallId = this[SeanceTable.hallId]!!*/
        )

}
fun ResultRow?.toSchedule(): Schedule? {
    return if (this == null) null
    else Schedule(
        id = this[ScheduleTable.id],
        seanceDate = this[ScheduleTable.seanceDate].toString(),
        seanceTime = this[ScheduleTable.seanceTime].toString()
    )
}

fun ResultRow?.toHall(): Hall? {
    return if (this == null) null
    else Hall(
        id = this[HallTable.Id],
        Name = this[HallTable.HallName],
        Seats = this[HallTable.initialSeatsAmount],
        availableSeatsAmount = this[HallTable.availableSeatsAmount]
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
