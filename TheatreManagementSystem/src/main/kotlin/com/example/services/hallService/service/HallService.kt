package com.example.services.hallService.service

import com.example.db.model.Hall
import com.example.db.model.Seats
import com.example.request.hall.GetSeatsRequest
import com.example.request.hall.HallAddRequest

interface HallService {
    suspend fun add(hallAddRequest: HallAddRequest): Hall?
    suspend fun getAll(): List<Hall>
    suspend fun getAllSeats(getSeatsRequest: GetSeatsRequest): List<Seats>?
//    suspend fun get(hallName: String): List<Seats>
}