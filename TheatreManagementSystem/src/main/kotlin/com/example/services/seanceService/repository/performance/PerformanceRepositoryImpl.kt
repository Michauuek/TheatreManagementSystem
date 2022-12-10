package com.example.services.seanceService.repository.performance

import com.example.db.DatabaseFactory
import com.example.db.extension.toPerformance
import com.example.db.schemas.PerformanceTable
import com.example.db.model.Performance
import com.example.request.seance.PerformanceRequest
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class PerformanceRepositoryImpl: PerformanceRepository {
    override suspend fun add(performanceRequest: PerformanceRequest): Performance? {
        var statement = DatabaseFactory.dbQuery {
            PerformanceTable.insert {
                it[title] = performanceRequest.title
                it[description] = performanceRequest.description
                it[imageUrl] = performanceRequest.imageUrl
            }
        }
        return statement.resultedValues?.first().toPerformance()
    }

    override suspend fun getById(id: Int): Performance? =
        DatabaseFactory.dbQuery{
            PerformanceTable
                .select { PerformanceTable.performanceId eq id }
                .map{ it.toPerformance()}
                .singleOrNull()
    }

    override suspend fun getByName(performanceName: String): Performance? =
        DatabaseFactory.dbQuery{
            PerformanceTable
                .select { PerformanceTable.title eq performanceName }
                .map{ it.toPerformance()}
                .singleOrNull()
    }

    override suspend fun getAll(): List<Performance> =
        DatabaseFactory.dbQuery{
            PerformanceTable
                .selectAll()
                .mapNotNull{ it.toPerformance()}
    }

    override suspend fun getPage(page: Int, size: Int): List<Performance> {
        val limit: Int = size
        val pageSize: Int = size
        val skip: Long = (page - 1) * pageSize.toLong()
        return  DatabaseFactory.dbQuery{
            PerformanceTable.selectAll().limit(n = limit, offset = skip).mapNotNull {
            it.toPerformance() }
        }
    }
}