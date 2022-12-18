package com.example.db.schemas

import org.jetbrains.exposed.sql.Table

/**
 * ## Table for storing reserved seats
 *
 * This table is used to store reserved seats for given Reservation (and seance).
 *
 * @see com.example.db.schemas.ReservationTable
 * @see com.example.db.schemas.SeanceTable
 * @see com.example.services.reservationService
 */
object ReservedSeatsTable : Table("ReservedSeats") {
    /**
     * ## Reserveation ID for this seat
     *
     * this is reservation related to this seat
     *
     * @see com.example.db.schemas.ReservationTable.reservationId
     */
    val reservationId = integer("id_reservation").references(ReservationTable.reservationId)

    /**
     * ## Seat ID for this seat
     *
     * Seat that is reserved
     *
     * @see com.example.db.schemas.SeatsTable.seatId
     */
    val seatId = integer("seat_id").references(SeatsTable.seatId)

    override val primaryKey = PrimaryKey(reservationId, seatId)
}