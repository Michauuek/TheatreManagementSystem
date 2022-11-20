package com.example.data.repository.performance

import com.example.data.db.DatabaseFactory
import com.example.data.db.extension.toActor
import com.example.data.db.extension.toPerformance
import com.example.data.db.schemas.ActorTable
import com.example.data.db.schemas.PerformanceTable
import com.example.data.model.Actor
import com.example.data.model.Performance
import com.example.data.request.ActorRequest
import com.example.data.request.PerformanceRequest
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
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
                it[castId] = performanceRequest.castId
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