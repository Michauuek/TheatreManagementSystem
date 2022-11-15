package com.example.data.service.hall

import com.example.data.model.Hall
import com.example.data.model.Seats
import com.example.data.request.GetSeatsRequest
import com.example.data.request.HallRequest

interface HallService {
    suspend fun add(hallRequest: HallRequest): Hall?
    suspend fun getAll(): List<Hall>
    suspend fun getAllSeats(getSeatsRequest: GetSeatsRequest): List<Seats>?

}