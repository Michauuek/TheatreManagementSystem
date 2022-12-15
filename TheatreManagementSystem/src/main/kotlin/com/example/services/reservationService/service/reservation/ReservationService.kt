package com.example.services.reservationService.service.reservation

import com.example.db.model.Reservation
import com.example.exception.ParsingException
import com.example.request.reservation.AddReservationRequest
import com.example.services.reservationService.repository.reservation.ReservationRepository

class ReservationService(
    private val reservationRepository: ReservationRepository,
    //private val emailRegex: Regex = """(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])""".toRegex(),
    private val phoneRegex: Regex = """\+\d{1,3}[\ -]?\d{3}[\ -]?\d{3}[\ -]?\d{3}""".toRegex(),
) {
    suspend fun add(reservationRequest: AddReservationRequest): Reservation? {
        /*val emailValidation = emailRegex
            .matchEntire(reservationRequest.clientEmail)*/
        val nameValidation = reservationRequest.clientName.length > 3
        val phoneNumberValidation = phoneRegex
            .matchEntire(reservationRequest.phoneNumber)
        /*if(emailValidation == null)
            throw ParsingException("Wrong email!")*/
        if(!nameValidation)
            throw ParsingException("Wrong name!")
        if(phoneNumberValidation == null)
            throw ParsingException("Wrong phone number!")
        return reservationRepository.add(reservationRequest)
    }

    suspend fun getAllReservationsForSeance(seanceId: Int): List<Reservation> {
        return reservationRepository.getAllReservationsForSeance(seanceId)
    }

    suspend fun getAll(): List<Reservation> {
        TODO("Not yet implemented")
    }
}