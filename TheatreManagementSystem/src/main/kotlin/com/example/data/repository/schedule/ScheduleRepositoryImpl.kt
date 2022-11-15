package com.example.data.repository.schedule

class ScheduleRepositoryImpl: ScheduleRepository {
   /* override suspend fun add(scheduleRequest: ScheduleRequest): Schedule? {
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
    }*/

}