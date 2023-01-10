package com.example.services.reservationService.service.reservation

import com.example.db.model.Reservation
import com.example.exception.ParsingException
import com.example.exception.ValidationException
import com.example.request.reservation.AddReservation
import com.example.request.reservation.AddReservationRequest
import com.example.response.reservation.AllReservations
import com.example.services.reservationService.repository.reservation.ReservationRepository
import com.example.services.reservationService.repository.reservedSeats.ReservedSeatsRepository
import java.time.LocalDateTime

class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val reservedSeatsRepository: ReservedSeatsRepository
) {
    val SEND_CONFIRMATION_EMAIL = true;
    val USE_EMAIL_VALIDATOR = false
    val USE_NAME_VALIDATOR = false;
    val USE_PHONE_VALIDATOR = false;
    val USE_IP_VALIDATOR = false;

    suspend fun add(reservationRequest: AddReservation): Reservation? {
        val emailValidation = Validator.validateEmail(reservationRequest.clientEmail)
        if (!emailValidation && USE_EMAIL_VALIDATOR)
            throw ParsingException("Wrong email!")

        val nameValidation = Validator.validateName(reservationRequest.clientName)
        if (!nameValidation && USE_NAME_VALIDATOR)
            throw ParsingException("Wrong name!")

        val phoneNumberValidation = Validator.validatePhoneNumber(reservationRequest.clientPhone!!)
        if (!phoneNumberValidation && reservationRequest.reservationAuthMode != "Auth" && USE_PHONE_VALIDATOR)
            throw ParsingException("Wrong phone number!")

        val ipValidation = Validator.validateIP(reservationRequest.reservationIPAddress)
        if (!ipValidation && USE_IP_VALIDATOR)
            throw ParsingException("Blacklisted IP")

        val reservation = reservationRepository.add(reservationRequest)

        if (SEND_CONFIRMATION_EMAIL) {
            val emailSender = EmailSender(reservationRequest.clientEmail)
            emailSender.sendEmail()
        }

        return reservation
    }

    suspend fun getAllReservationsForSeance(seanceId: Int): AllReservations {
        val reservations = reservationRepository.getAllReservationsForSeance(seanceId)

        return AllReservations(
            seanceId = seanceId,
            reservations = reservations
        )
    }

    suspend fun getAllReservedSeatsForSeance(seanceId: Int): List<Int> {
        return reservationRepository.getAllReservedSeatsForSeance(seanceId)
    }

    suspend fun getAll(): List<Reservation> {
        return reservationRepository.getAll()
    }

    suspend fun deleteById(reservationId: Int?) {
        if (reservationId == null || reservationId < 0)
            throw ValidationException("Id cannot be null!")
        reservedSeatsRepository.deleteByReservationId(reservationId)
        reservationRepository.deleteById(reservationId)
    }
}