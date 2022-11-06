package com.example.data.repository.seance

import com.example.data.db.DatabaseFactory
import com.example.data.db.extension.toSeance
import com.example.data.db.schemas.SeanceTable
import com.example.data.model.Seance
import com.example.data.request.SeanceRequest
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.InsertStatement

class SeanceRepositoryImpl: SeanceRepository {

    override suspend fun addSeance(seanceRequest: SeanceRequest): Seance? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = SeanceTable.insert {
                it[title] = seanceRequest.title
                it[genre] = seanceRequest.genre
                it[director] = seanceRequest.director
                it[duration] = seanceRequest.duration
                it[price] = seanceRequest.price
                /*it[scheduleId] = seanceRequest.scheduleId
                it[hallId] = seanceRequest.scheduleId*/

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