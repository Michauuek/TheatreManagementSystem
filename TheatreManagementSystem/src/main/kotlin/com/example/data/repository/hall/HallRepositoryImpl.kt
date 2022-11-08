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
        // dbQuerry zwraca wartość więc nie trzeba statement robić

        //var statement: InsertStatement<Number>? = null

        var statement = DatabaseFactory.dbQuery {
            HallTable.insert {
                it[HallName] = hallRequest.HallName
                it[SeatsLayout] = hallRequest.SeatsLayout
                it[BackgroundPath] = hallRequest.BackgroundPath
            }
        }
        return statement.resultedValues?.first().toHall()
    }

    override suspend fun getAll(): List<Hall> = DatabaseFactory.dbQuery{
        HallTable
            .selectAll()
            .mapNotNull{ it.toHall()}
    }
}