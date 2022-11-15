package com.example.data.repository.actor

import com.example.data.db.DatabaseFactory
import com.example.data.db.extension.toActor
import com.example.data.db.extension.toHall
import com.example.data.db.schemas.ActorTable
import com.example.data.db.schemas.HallTable
import com.example.data.model.Actor
import com.example.data.request.ActorRequest
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class ActorRepositoryImpl: ActorRepository {
    override suspend fun add(actorRequest: ActorRequest): Actor? {
        var statement = DatabaseFactory.dbQuery {
            ActorTable.insert {
                it[name] = actorRequest.name
                it[surname] = actorRequest.surname
            }
        }
        return statement.resultedValues?.first().toActor()
    }

    override suspend fun getById(id: Int): Actor? = DatabaseFactory.dbQuery{
        ActorTable
            .select { ActorTable.actorId eq id }
            .map{ it.toActor()}
            .singleOrNull()
    }

    override suspend fun getAll(): List<Actor> = DatabaseFactory.dbQuery{
        ActorTable
            .selectAll()
            .mapNotNull{ it.toActor()}
    }
}