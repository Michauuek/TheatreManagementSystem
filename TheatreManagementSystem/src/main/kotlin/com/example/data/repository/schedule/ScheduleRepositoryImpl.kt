package com.example.data.repository.schedule

import com.example.data.db.DatabaseFactory
import com.example.data.db.extension.toSchedule
import com.example.data.db.extension.toSeance
import com.example.data.db.schemas.ScheduleTable
import com.example.data.db.schemas.SeanceTable
import com.example.data.model.Schedule
import com.example.data.request.ScheduleRequest
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.time.LocalDate
import java.time.LocalTime

class ScheduleRepositoryImpl: ScheduleRepository {
    override suspend fun add(scheduleRequest: ScheduleRequest): Schedule? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = ScheduleTable.insert {
                it[seanceDate] = LocalDate.parse(scheduleRequest.seanceDate)
                it[seanceTime] = LocalTime.parse(scheduleRequest.seanceTime)
            }
        }
        return statement?.resultedValues?.first().toSchedule()
    }

    override suspend fun getAll(): List<Schedule> = DatabaseFactory.dbQuery{
        ScheduleTable
            .selectAll()
            .mapNotNull{ it.toSchedule()}
    }

}