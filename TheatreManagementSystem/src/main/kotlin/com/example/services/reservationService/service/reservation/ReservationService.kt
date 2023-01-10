package com.example.services.reservationService.service.reservation

import com.example.db.model.Reservation
import com.example.exception.ParsingException
import com.example.exception.ValidationException
import com.example.request.reservation.AddReservation
import com.example.request.reservation.AddReservationRequest
import com.example.response.reservation.AllReservations
import com.example.response.reservation.ReservationResponse
import com.example.response.reservation.ReservationStatus
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

    suspend fun add(reservationRequest: AddReservation): ReservationResponse {
        val emailValidation = Validator.validateEmail(reservationRequest.clientEmail)
        if (!emailValidation && USE_EMAIL_VALIDATOR)
            return ReservationResponse(ReservationStatus.INVALID_EMAIL)

        val nameValidation = Validator.validateName(reservationRequest.clientName)
        if (!nameValidation && USE_NAME_VALIDATOR)
            return ReservationResponse(ReservationStatus.INVALID_NAME)

        if(reservationRequest.reservationAuthMode != "oauth" && USE_PHONE_VALIDATOR) {
            if (reservationRequest.clientPhone == null)
                return ReservationResponse(ReservationStatus.INVAILD_NUMBER)
            val phoneNumberValidation = Validator.validatePhoneNumber(reservationRequest.clientPhone)
            if (!phoneNumberValidation)
                return ReservationResponse(ReservationStatus.INVAILD_NUMBER)
        }

        val ipValidation = Validator.validateIP(reservationRequest.reservationIPAddress)
        if (!ipValidation && USE_IP_VALIDATOR)
            return ReservationResponse(ReservationStatus.OTHER_ERROR)

        reservationRepository.add(reservationRequest)
            ?: return ReservationResponse(ReservationStatus.SEATS_ALREADY_RESERVED)

        if (SEND_CONFIRMATION_EMAIL) {
            val emailSender = EmailSender(reservationRequest.clientEmail)
            emailSender.sendEmail()
        }

        return ReservationResponse(ReservationStatus.OK)
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