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

    suspend fun add(reservationRequest: AddReservation): Reservation? {
        val emailValidation = Validator.validateEmail(reservationRequest.clientEmail)
        val nameValidation = Validator.validateName(reservationRequest.clientName)
        val phoneNumberValidation = if (reservationRequest.reservationAuthMode == "form") Validator.validatePhoneNumber(reservationRequest.clientPhone!!) else true;

        //TODO repair validation
        /*if(!emailValidation)
            throw ParsingException("Wrong email!")*/
        if(!nameValidation)
            throw ParsingException("Wrong name!")
        // TODO that has to be smarter
//        if(!phoneNumberValidation)
//            throw ParsingException("Wrong phone number!")

        val reservation = reservationRepository.add(reservationRequest)

        if(SEND_CONFIRMATION_EMAIL) {
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

    /**
     * @return list of reserved seats ids
     */
    suspend fun getAllReservedSeatsForSeance(seanceId: Int): List<Int> {
        return reservationRepository.getAllReservedSeatsForSeance(seanceId)
    }

    suspend fun getAll(): List<Reservation> {
        return reservationRepository.getAll()
    }
    suspend fun deleteById(reservationId: Int?) {
        if(reservationId == null || reservationId < 0)
            throw ValidationException("Id cannot be null!")
        reservedSeatsRepository.deleteByReservationId(reservationId)
        reservationRepository.deleteById(reservationId)
    }
}