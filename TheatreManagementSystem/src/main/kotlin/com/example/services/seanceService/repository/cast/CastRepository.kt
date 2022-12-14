package com.example.services.seanceService.repository.cast

import com.example.db.DatabaseFactory
import com.example.db.extension.toCast
import com.example.db.schemas.CastTable
import com.example.db.model.Cast
import com.example.request.seance.CastRequest
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class CastRepository {
    suspend fun add(castRequest: CastRequest): Cast? {

        var statement = DatabaseFactory.dbQuery {
            CastTable.insert {
                it[actorId] = castRequest.actorId
                it[role] = castRequest.role
            }
        }
        return statement.resultedValues?.first().toCast()
    }

    suspend fun getById(id: Int): Cast? = DatabaseFactory.dbQuery{
        //TODO tutaj jest klucz złożony
        CastTable
            .select { CastTable.actorId eq id }
            .map{ it.toCast()}
            .singleOrNull()
    }

    suspend fun getAll(): List<Cast> = DatabaseFactory.dbQuery{
        CastTable
            .selectAll()
            .mapNotNull{ it.toCast()}
    }
    suspend fun deleteByPerformanceId(id: Int) {
        DatabaseFactory.dbQuery {
            CastTable.deleteWhere{ CastTable.performanceId eq id}
        }
    }
}