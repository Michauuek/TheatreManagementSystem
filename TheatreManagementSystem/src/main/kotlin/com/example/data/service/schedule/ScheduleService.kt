package com.example.data.service.schedule

import com.example.data.model.Hall
import com.example.data.model.Schedule
import com.example.data.request.HallRequest
import com.example.data.request.ScheduleRequest

interface ScheduleService {
    suspend fun add(scheduleRequest: ScheduleRequest): Schedule?
    suspend fun getAll(): List<Schedule>
}