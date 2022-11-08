package com.example.data.db.extension

import com.example.data.db.schemas.*
import com.example.data.model.*
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow?.toSeance(): Seance? {
    return if (this == null) null
    else Seance(
        id = this[SeanceTable.id],
        HallId = this[SeanceTable.HallId],
        PerformanceId = this[SeanceTable.PerformanceId],
        seanceDate = this[SeanceTable.seanceDate].toString(),
        seanceTime = this[SeanceTable.seanceTime].toString(),
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
    return if (this == null)
    {
        null
    }
    else
    {
        Hall(
            HallName = this[HallTable.HallName],
            SeatsLayout = this[HallTable.SeatsLayout],
            BackgroundPath = this[HallTable.BackgroundPath]
        )
    }
}
fun ResultRow?.toUser(): User? {
    return if (this == null) null
    else User(
        id = this[UserTable.id],
        email = this[UserTable.email],
        password = this[UserTable.password]
    )
}

fun ResultRow?.toReservation(): Reservation? {
    return if (this == null) null
    else Reservation(
        id = this[ReservationTable.id],
        ReservationDate = this[ReservationTable.ReservationDate].toString(),
        ReservationTime = this[ReservationTable.ReservationTime].toString(),
        ClientName = this[ReservationTable.ClientName],
        ClientEmail = this[ReservationTable.ClientEmail],
    )
}
