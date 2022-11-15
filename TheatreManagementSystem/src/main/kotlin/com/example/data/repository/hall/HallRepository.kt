package com.example.data.repository.hall

import com.example.data.model.Hall
import com.example.data.model.Seats
import com.example.data.request.GetSeatsRequest
import com.example.data.request.HallRequest
import com.example.data.request.ScheduleRequest

interface HallRepository {
    suspend fun add(hallRequest: HallRequest): Hall?
    suspend fun getAll(): List<Hall>

    suspend fun getAllSeats(getSeatsRequest: GetSeatsRequest): List<Seats>?
}