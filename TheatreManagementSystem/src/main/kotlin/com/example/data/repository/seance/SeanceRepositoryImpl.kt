package com.example.data.repository.seance

import com.example.data.db.DatabaseFactory
import com.example.data.db.extension.toSeance
import com.example.data.db.schemas.ScheduleTable
import com.example.data.db.schemas.SeanceTable
import com.example.data.model.Seance
import com.example.data.request.SeanceRequest
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.time.LocalDate
import java.time.LocalTime

class SeanceRepositoryImpl: SeanceRepository {

    override suspend fun addSeance(seanceRequest: SeanceRequest): Seance? {
        var statement = DatabaseFactory.dbQuery {
            SeanceTable.insert {
                it[PerformanceId]  = seanceRequest.PerformanceId
                it[seanceDate] = LocalDate.parse(seanceRequest.seanceDate)
                it[seanceTime] = LocalTime.parse(seanceRequest.seanceTime)
            }
        }

        return statement?.resultedValues?.first().toSeance()
    }

    override suspend fun getAllSeances(): List<Seance> = DatabaseFactory.dbQuery{
        SeanceTable
            .selectAll()
            .mapNotNull{ it.toSeance()}
    }
}