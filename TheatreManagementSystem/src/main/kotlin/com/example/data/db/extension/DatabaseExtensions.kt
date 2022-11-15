package com.example.data.db.extension

import com.example.data.db.schemas.*
import com.example.data.model.*
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow?.toSeance(): Seance? {
    return if (this == null) null
    else Seance(
        id = this[SeanceTable.id],
        HallId = this[SeanceTable.hallId],
        PerformanceId = this[SeanceTable.performanceId],
        seanceDate = this[SeanceTable.seanceDate].toString(),
        seanceTime = this[SeanceTable.seanceTime].toString(),
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
            HallName = this[HallTable.hallName],
            BackgroundPath = this[HallTable.backgroundPath]
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
        id = this[ReservationTable.reservationId],
        ReservationDate = this[ReservationTable.reservationDate].toString(),
        ReservationTime = this[ReservationTable.reservationTime].toString(),
        ClientName = this[ReservationTable.clientName],
        ClientEmail = this[ReservationTable.clientEmail],
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

