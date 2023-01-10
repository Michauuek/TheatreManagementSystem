package com.example.services.seanceService.repository.seance

import com.example.db.DatabaseFactory
import com.example.db.extension.toSeance
import com.example.db.extension.toSeanceExtendedResponse
import com.example.db.model.Seance
import com.example.db.schemas.PerformanceTable
import com.example.db.schemas.SeanceTable
import com.example.request.seance.SeanceRequest
import com.example.response.seance.SeanceExtendedResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.between
import java.time.LocalDate
import java.time.LocalTime

class SeanceRepository {

    suspend fun addSeance(seanceRequest: SeanceRequest): Seance? {
        val statement = DatabaseFactory.dbQuery {
            SeanceTable.insert {
                it[hallName] = seanceRequest.hallName
                it[performanceId]  = seanceRequest.performanceId
                it[seanceDate] = LocalDate.parse(seanceRequest.seanceDate)
                it[seanceTime] = LocalTime.parse(seanceRequest.seanceTime)
            }
        }

        return statement.resultedValues?.first().toSeance()
    }

    suspend fun getAllSeances(): List<Seance> =
        DatabaseFactory.dbQuery{
            SeanceTable
                .selectAll()
                .orderBy(SeanceTable.seanceDate to SortOrder.ASC)
                .orderBy(SeanceTable.seanceTime to SortOrder.ASC)
                .mapNotNull{ it.toSeance()}
    }
    suspend fun getSeancesBetweenDates(fromDate: LocalDate, toDate: LocalDate): List<Seance> =
        DatabaseFactory.dbQuery{
            SeanceTable
                .select(SeanceTable.seanceDate.between(fromDate,toDate))
                .orderBy(SeanceTable.seanceDate to SortOrder.ASC)
                .orderBy(SeanceTable.seanceTime to SortOrder.ASC)
                .mapNotNull{ it.toSeance()}
        }
    suspend fun getDetailedSeancesBetweenDates(fromDate: LocalDate,toDate: LocalDate): List<SeanceExtendedResponse> =
        DatabaseFactory.dbQuery{
            (SeanceTable innerJoin PerformanceTable)
                .select(SeanceTable.seanceDate.between(fromDate,toDate))
                .orderBy(SeanceTable.seanceDate to SortOrder.ASC)
                .orderBy(SeanceTable.seanceTime to SortOrder.ASC)
                .mapNotNull{ it.toSeanceExtendedResponse()}
        }
    suspend fun getDetailedSeances(): List<SeanceExtendedResponse> =
        DatabaseFactory.dbQuery{
            (SeanceTable innerJoin PerformanceTable)
                .selectAll()
                .orderBy(SeanceTable.seanceDate to SortOrder.ASC)
                .orderBy(SeanceTable.seanceTime to SortOrder.ASC)
                .mapNotNull{ it.toSeanceExtendedResponse()}
        }

    suspend fun getDetailed(seanceId : Int): SeanceExtendedResponse? =
        DatabaseFactory.dbQuery{
            (SeanceTable innerJoin PerformanceTable)
                .select{ SeanceTable.id eq seanceId}
                .orderBy(SeanceTable.seanceDate to SortOrder.ASC)
                .orderBy(SeanceTable.seanceTime to SortOrder.ASC)
                .map{ it.toSeanceExtendedResponse()}.singleOrNull()
        }

    suspend fun getSeancesBetweenDatesByPerformanceId(fromDate: LocalDate, toDate: LocalDate, id: Int): List<Seance> =
        DatabaseFactory.dbQuery{
            (SeanceTable innerJoin PerformanceTable)
                .select{ PerformanceTable.performanceId eq id and SeanceTable.seanceDate.between(fromDate,toDate)}
                .orderBy(SeanceTable.seanceDate to SortOrder.ASC)
                .orderBy(SeanceTable.seanceTime to SortOrder.ASC)
                .mapNotNull{ it.toSeance()}
        }

    suspend fun deleteById(seanceId: Int) {
        DatabaseFactory.dbQuery {
            SeanceTable.deleteWhere{SeanceTable.id eq seanceId}
        }
    }
}
