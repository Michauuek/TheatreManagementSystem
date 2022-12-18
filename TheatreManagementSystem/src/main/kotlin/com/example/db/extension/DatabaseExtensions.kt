package com.example.db.extension

import com.example.db.model.*
import com.example.db.schemas.*
import com.example.response.actor.ActorCastResponse
import com.example.response.seance.SeanceExtendedResponse
import org.jetbrains.exposed.sql.ResultRow



fun ResultRow?.toActor(): Actor?{
    return if (this == null) null
    else Actor(
        id = this[ActorTable.actorId],
        name = this[ActorTable.name],
        surname = this[ActorTable.surname],
        photoUrl = this[ActorTable.photoUrl]
    )
}

fun ResultRow?.toPerformance(): Performance?{
    return if (this == null) null
    else Performance(
        performanceId = this[PerformanceTable.performanceId],
        title = this[PerformanceTable.title],
        description = this[PerformanceTable.description],
        imageUrl = this[PerformanceTable.imageUrl]
    )
}

fun ResultRow?.toCast(): Cast?{
    return if (this == null) null
    else Cast(
        performanceId = this[CastTable.performanceId],
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
        castId = this[PerformanceTable.performanceId],
        imageUrl = this[PerformanceTable.imageUrl]
    )
}

fun ResultRow?.toHall(): Hall? {
    return if (this == null) null
    else Hall(
            hallName = this[HallTable.hallName],
            backgroundPath = this[HallTable.backgroundPath]
        )
}

fun ResultRow?.toReservation(): Reservation? {
    return if (this == null) null
    else Reservation(
        id = this[ReservationTable.reservationId],
        reservationDate = this[ReservationTable.date].toString(),
        reservationTime = this[ReservationTable.time].toString(),
        clientName = this[ReservationTable.clientName],
        clientEmail = this[ReservationTable.clientEmail],
        reservedSeats = listOf(),
        seanceId = this[ReservationTable.seanceId]
    )
}

fun ResultRow?.toSeat(): Seats? {
    return if (this == null) null
    else Seats(
        id = this[SeatsTable.seatId],
        hallName = this[SeatsTable.hallName],
        seatName = this[SeatsTable.seatName],
        posX = this[SeatsTable.posX],
        posY = this[SeatsTable.posY],
    )
}
fun ResultRow?.toActorCastResponse(): ActorCastResponse? {
    return if (this == null) null
    else ActorCastResponse(
        actorId = this[ActorTable.actorId],
        name = this[ActorTable.name],
        surname = this[ActorTable.surname],
        photoUrl = this[ActorTable.photoUrl],
        performanceId = this[CastTable.performanceId],
        role = this[CastTable.role],
    )
}

