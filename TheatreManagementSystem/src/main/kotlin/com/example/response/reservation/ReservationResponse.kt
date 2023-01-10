package com.example.response.reservation

import kotlinx.serialization.Serializable

enum class ReservationStatus {
    OK,
    SEATS_ALREADY_RESERVED,
    INVALID_NAME,
    INVALID_EMAIL,
    INVAILD_NUMBER,
    OTHER_ERROR,
}
@Serializable
data class ReservationResponse(
    val status: ReservationStatus,
)