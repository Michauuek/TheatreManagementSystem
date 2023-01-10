package com.example.services.seanceService.repository.performance

import com.example.db.DatabaseFactory
import com.example.db.extension.toPerformance
import com.example.db.extension.toSeanceExtendedResponse
import com.example.db.model.Performance
import com.example.db.schemas.PerformanceTable
import com.example.db.schemas.SeanceTable
import com.example.request.seance.PerformanceRequest
import com.example.response.seance.SeanceExtendedResponse
import org.jetbrains.exposed.sql.*

class PerformanceRepository {
    suspend fun add(performanceRequest: PerformanceRequest): Performance? {
        var statement = DatabaseFactory.dbQuery {
            PerformanceTable.insert {
                it[title] = performanceRequest.title
                it[description] = performanceRequest.description
                it[imageUrl] = performanceRequest.imageUrl
                it[length] = performanceRequest.length
            }
        }
        return statement.resultedValues?.first().toPerformance()
    }

    suspend fun getById(id: Int): Performance? =
        DatabaseFactory.dbQuery{
            PerformanceTable
                .select { PerformanceTable.performanceId eq id }
                .map{ it.toPerformance()}
                .singleOrNull()
    }

    suspend fun getByName(performanceName: String): Performance? =
        DatabaseFactory.dbQuery{
            PerformanceTable
                .select { PerformanceTable.title eq performanceName }
                .map{ it.toPerformance()}
                .singleOrNull()
    }

    suspend fun getAll(): List<Performance> =
        DatabaseFactory.dbQuery{
            PerformanceTable
                .selectAll()
                .mapNotNull{ it.toPerformance()}
    }

    suspend fun getPage(page: Int, size: Int): List<Performance> {
        val limit: Int = size
        val pageSize: Int = size
        val skip: Long = (page - 1) * pageSize.toLong()
        return  DatabaseFactory.dbQuery{
            PerformanceTable.selectAll().limit(n = limit, offset = skip).mapNotNull {
            it.toPerformance() }
        }
    }



    suspend fun getDetailed(performanceId : Int): List<SeanceExtendedResponse> =
        DatabaseFactory.dbQuery{
            (SeanceTable innerJoin PerformanceTable)
                .select{ PerformanceTable.performanceId eq performanceId}
                .orderBy(SeanceTable.seanceDate to SortOrder.ASC)
                .orderBy(SeanceTable.seanceTime to SortOrder.ASC)
                .mapNotNull{ it.toSeanceExtendedResponse()}
        }

    suspend fun deleteById(performanceId: Int) {
        DatabaseFactory.dbQuery {
            PerformanceTable.deleteWhere{PerformanceTable.performanceId eq performanceId}
        }
    }
}