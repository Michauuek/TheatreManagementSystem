package com.example.services.reservationService.service.reservation

import com.example.services.reservationService.repository.reservation.ReservationRepository

class Validator {
    private val phoneRegex: Regex = """\+\d{1,3}[\ -]?\d{3}[\ -]?\d{3}[\ -]?\d{3}""".toRegex()
    private val emailRegex = """^[A-Za-z](.*)(@)(.+)(\\.)(.+)""".toRegex()
    fun validatePhoneNumber(phoneNumber: String) =
        phoneRegex.matches(phoneNumber)

    fun validateEmail(email: String) =
        emailRegex.matches(email)
}