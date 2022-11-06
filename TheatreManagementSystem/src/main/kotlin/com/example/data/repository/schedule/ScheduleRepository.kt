package com.example.data.repository.schedule

import com.example.data.model.Schedule
import com.example.data.model.Seance
import com.example.data.request.ScheduleRequest
import com.example.data.request.SeanceRequest

interface ScheduleRepository {
    suspend fun add(scheduleRequest: ScheduleRequest): Schedule?
    suspend fun getAll(): List<Schedule>
}