package com.example.data.db.extension

import com.example.data.db.schemas.*
import com.example.data.model.*
import com.example.data.response.SeanceExtendedResponse
import org.jetbrains.exposed.sql.ResultRow



fun ResultRow?.toActor(): Actor?{
    return if (this == null) null
    else Actor(
        id = this[ActorTable.actorId],
        name = this[ActorTable.name],
        surname = this[ActorTable.surname]
    )
}

fun ResultRow?.toPerformance(): Performance?{
    return if (this == null) null
    else Performance(
        performanceId = this[PerformanceTable.performanceId],
        title = this[PerformanceTable.title],
        description = this[PerformanceTable.description],
        imageUrl = this[PerformanceTable.imageUrl],
        castId = this[PerformanceTable.castId]
    )
}

fun ResultRow?.toCast(): Cast?{
    return if (this == null) null
    else Cast(
        id = this[CastTable.castId],
        actorId = this[CastTable.actorId],
        role = this[CastTable.role]
    )
}
fun ResultRow?.toSeance(): Seance? {
    return if (this == null) null
    else Seance(
        id = this[SeanceTable.id],
        hallName = this[SeanceTable.hallName],
        performanceId = this[SeanceTable.performanceId],
        seanceDate = this[SeanceTable.seanceDate].toString(),
        seanceTime = this[SeanceTable.seanceTime].toString(),
    )
}
fun ResultRow?.toSeanceExtendedResponse(): SeanceExtendedResponse? {
    return if (this == null) null
    else SeanceExtendedResponse(
        id = this[SeanceTable.id],
        hallName = this[SeanceTable.hallName],
        performanceId = this[SeanceTable.performanceId],
        seanceDate = this[SeanceTable.seanceDate].toString(),
        seanceTime = this[SeanceTable.seanceTime].toString(),
        title = this[PerformanceTable.title].toString(),
        description = this[PerformanceTable.description].toString(),
        castId = this[PerformanceTable.castId]
    )
}

fun ResultRow?.toHall(): Hall? {
    return if (this == null) null
    else Hall(
            hallName = this[HallTable.hallName],
            backgroundPath = this[HallTable.backgroundPath]
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

fun ResultRow?.toReservation(): Reservation? {
    return if (this == null) null
    else Reservation(
        id = this[ReservationTable.reservationId],
        reservationDate = this[ReservationTable.reservationDate].toString(),
        reservationTime = this[ReservationTable.reservationTime].toString(),
        clientName = this[ReservationTable.clientName],
        clientEmail = this[ReservationTable.clientEmail],
        reservedSeats = null,
    )
}

fun ResultRow?.toSeat(): Seats? {
    return if (this == null) null
    else Seats(
        id = this[SeatsTable.seatID],
        hallName = this[SeatsTable.hallName],
        seatName = this[SeatsTable.seatName],
        posX = this[SeatsTable.posX],
        posY = this[SeatsTable.posY],
    )
}

