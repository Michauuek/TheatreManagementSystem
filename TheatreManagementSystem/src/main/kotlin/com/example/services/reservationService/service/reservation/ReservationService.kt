package com.example.services.reservationService.service.reservation

import com.example.db.model.Reservation
import com.example.exception.ParsingException
import com.example.request.reservation.AddReservationRequest
import com.example.response.reservation.AllReservations
import com.example.services.reservationService.repository.reservation.ReservationRepository

class ReservationService(
    private val reservationRepository: ReservationRepository
) {
    suspend fun add(reservationRequest: AddReservationRequest): Reservation? {
        val emailValidation = Validator.validateEmail(reservationRequest.clientEmail)
        val nameValidation = reservationRequest.clientName.length > 3
        val phoneNumberValidation = Validator.validatePhoneNumber(reservationRequest.phoneNumber)

        //TODO repair validation
        /*if(!emailValidation)
            throw ParsingException("Wrong email!")
        if(!nameValidation)
            throw ParsingException("Wrong name!")
        if(!phoneNumberValidation)
            throw ParsingException("Wrong phone number!")*/

        val reservation = reservationRepository.add(reservationRequest)

        //send email
        val emailSender = EmailSender(reservationRequest.clientEmail)
        emailSender.sendEmail()

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
}