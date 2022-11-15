package com.example.data.repository.cast

import com.example.data.db.DatabaseFactory
import com.example.data.db.extension.toActor
import com.example.data.db.extension.toCast
import com.example.data.db.schemas.ActorTable
import com.example.data.db.schemas.CastTable
import com.example.data.model.Cast
import com.example.data.repository.actor.ActorRepository
import com.example.data.repository.actor.ActorRepositoryImpl
import com.example.data.request.CastRequest
import io.ktor.server.plugins.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class CastRepositoryImpl: CastRepository {
    override suspend fun add(castRequest: CastRequest): Cast? {

        var statement = DatabaseFactory.dbQuery {
            CastTable.insert {
                it[actorId] = castRequest.actorId
                it[role] = castRequest.role
            }
        }
        return statement.resultedValues?.first().toCast()
    }

    override suspend fun getById(id: Int): Cast? = DatabaseFactory.dbQuery{
        CastTable
            .select { CastTable.castId eq id }
            .map{ it.toCast()}
            .singleOrNull()
    }

    override suspend fun getAll(): List<Cast> = DatabaseFactory.dbQuery{
        CastTable
            .selectAll()
            .mapNotNull{ it.toCast()}
    }
}