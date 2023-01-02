package com.example.db.schemas


import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.time

/**
 * ## Reservation table
 *
 * This table is used to store reservations. Each reservation has seats reserved in it.
 * Users can reserve seats for performances and they are stored in this table.
 *
 * @see com.example.db.schemas.ReservedSeatsTable
 * @see com.example.db.schemas.SeanceTable
 * @see com.example.services.reservationService
 * @see com.example.services.seanceService
 */
object ReservationTable: Table("reservation") {
    /**
     * ## Unique id of reservation
     * ## PRIMARY KEY
     * ## AUTO_INCREMENT
     *
     * ID used to identify reservation in the database and in the application itself
     */
    val reservationId = integer("id").autoIncrement()

    /**
     * ## Reservation date
     * Date in which reservation was made example `2020-12-31`
     *
     */
    val date = date("reservation_date")
    /**
     * ## Reservation time
     * Time in which reservation was made example `12:00:00`
     *
     */
    val time = time("reservation_time")

    /**
     * ## Client Name
     * Client name displaed for admin to identify client, this is name provided by client in form or with oauth
     *
     */
    val clientName = varchar("client_name", 128)

    /**
     * ## Client Email
     * Client email provided in form or with oauth
     */
    val clientEmail = varchar("client_email", 128)

    /**
     * ## Phone number
     * Phone number provided by client in form, null if client used oauth
     */
    val clientPhone = varchar("phone_number", 128).nullable()


    /**
     * ## Seance id of this reservation
     * ## REFERENCES `SeanceTable.id`
     *
     * ID of the seance that this reservation is for
     *
     *
     * @see com.example.db.schemas.SeanceTable.id
     */
    val seanceId = integer("id_seance").references(SeanceTable.id)

    /**
     * ## Reservation IP Address
     */
    val reservationIPAddress = varchar("reservation_ip_address", 128)

    /**
     * ## Reservation Auth Mode
     * Mode in which client was authenticated
     * Possible values:
     * * `form`
     * * `oauth`
     * * `admin`
     */
    val reservationAuthMode = varchar("reservation_auth_mode", 128)

    // TODO: Add payment status (?)
    // TODO: Add reservation source (address)
    override val primaryKey = PrimaryKey(reservationId)
}