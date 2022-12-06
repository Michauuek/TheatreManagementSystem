package com.example.services.seanceService.repository.actor

import com.example.db.DatabaseFactory
import com.example.db.extension.toActor
import com.example.db.schemas.ActorTable
import com.example.db.model.Actor
import com.example.request.seance.ActorRequest
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