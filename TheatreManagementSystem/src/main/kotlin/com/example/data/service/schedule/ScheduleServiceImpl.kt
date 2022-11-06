package com.example.data.service.schedule

import com.example.data.model.Schedule
import com.example.data.repository.schedule.ScheduleRepository
import com.example.data.request.ScheduleRequest

class ScheduleServiceImpl(
    private val scheduleRepository: ScheduleRepository
):ScheduleService {

    override suspend fun add(scheduleRequest: ScheduleRequest): Schedule? {
        return scheduleRepository.add(scheduleRequest)
    }

    override suspend fun getAll(): List<Schedule> {
        return scheduleRepository.getAll()
    }
}