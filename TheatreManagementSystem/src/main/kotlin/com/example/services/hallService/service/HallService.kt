package com.example.services.hallService.service

import com.example.db.model.Hall
import com.example.db.model.Seats
import com.example.request.hall.GetSeatsRequest
import com.example.request.hall.HallRequest

interface HallService {
    suspend fun add(hallRequest: HallRequest): Hall?
    suspend fun getAll(): List<Hall>
    suspend fun getAllSeats(getSeatsRequest: GetSeatsRequest): List<Seats>?
}