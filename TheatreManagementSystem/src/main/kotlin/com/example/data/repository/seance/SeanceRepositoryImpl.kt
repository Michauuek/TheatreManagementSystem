package com.example.data.repository.seance

import com.example.data.db.DatabaseFactory
import com.example.data.db.extension.toSeance
import com.example.data.db.schemas.SeanceTable
import com.example.data.model.Seance
import com.example.data.request.SeanceRequest
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.between
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.time.LocalDate
import java.time.LocalTime

class SeanceRepositoryImpl: SeanceRepository {

    override suspend fun addSeance(seanceRequest: SeanceRequest): Seance? {
        val statement = DatabaseFactory.dbQuery {
            SeanceTable.insert {
                it[hallName] = seanceRequest.hallId
                it[performanceId]  = seanceRequest.performanceId
                it[seanceDate] = LocalDate.parse(seanceRequest.seanceDate)
                it[seanceTime] = LocalTime.parse(seanceRequest.seanceTime)
            }
        }

        return statement.resultedValues?.first().toSeance()
    }

    override suspend fun getAllSeances(): List<Seance> =
        DatabaseFactory.dbQuery{
            SeanceTable
                .selectAll()
                .orderBy(SeanceTable.seanceDate to SortOrder.ASC)
                .orderBy(SeanceTable.seanceTime to SortOrder.ASC)
                .mapNotNull{ it.toSeance()}
    }
    override suspend fun getSeancesBetweenDates(fromDate: LocalDate, toDate: LocalDate): List<Seance> =
        DatabaseFactory.dbQuery{
            SeanceTable
                .select(SeanceTable.seanceDate.between(fromDate,toDate))
                .orderBy(SeanceTable.seanceDate to SortOrder.ASC)
                .orderBy(SeanceTable.seanceTime to SortOrder.ASC)
                .mapNotNull{ it.toSeance()}
        }
}