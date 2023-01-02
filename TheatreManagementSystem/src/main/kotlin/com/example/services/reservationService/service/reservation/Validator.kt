package com.example.services.reservationService.service.reservation

import com.example.services.reservationService.repository.reservation.ReservationRepository

class Validator {
    companion object {
        private val phoneRegex: Regex = """\+\d{1,3}[\ -]?\d{3}[\ -]?\d{3}[\ -]?\d{3}""".toRegex()
        private val emailRegex = """^[A-Za-z](.*)(@)(.+)(\\.)(.+)""".toRegex()
        fun validatePhoneNumber(phoneNumber: String) =
            phoneRegex.matches(phoneNumber)
        fun validateEmail(email: String): Boolean {
            // check if email is syntactically correct
            if(!emailRegex.matches(email)) return false

            // maybe call some external service to check if email is not fake


            return true;
        }
        fun validateName(name: String) = name.length > 3

        fun validateIP(ip: String): Boolean {
            // check if ip is valid

            return true;
        }
        fun validateSeanse(seanceId: Int, reservedSeats: List<Int>): Boolean {
            // check if seance exists

            // check if seats are available

            return true;
        }
    }

}