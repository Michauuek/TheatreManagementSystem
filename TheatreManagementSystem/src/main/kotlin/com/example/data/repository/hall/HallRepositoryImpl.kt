package com.example.data.repository.hall

import com.example.data.db.DatabaseFactory
import com.example.data.db.extension.toHall
import com.example.data.db.extension.toSchedule
import com.example.data.db.schemas.HallTable
import com.example.data.db.schemas.ScheduleTable
import com.example.data.model.Hall
import com.example.data.request.HallRequest
import com.example.data.request.ScheduleRequest
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.time.LocalDate
import java.time.LocalTime

class HallRepositoryImpl: HallRepository {
    override suspend fun add(hallRequest: HallRequest): Hall? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = HallTable.insert {
                it[number] = hallRequest.number
                it[availableSeatsAmount] = hallRequest.availableSeatsAmount
                it[initialSeatsAmount] = hallRequest.initialSeatsAmount
            }
        }
        return statement?.resultedValues?.first().toHall()
    }

    override suspend fun getAll(): List<Hall> = DatabaseFactory.dbQuery{
        HallTable
            .selectAll()
            .mapNotNull{ it.toHall()}
    }
}