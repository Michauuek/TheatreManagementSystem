package com.example.services.hallService.repository

import com.example.db.DatabaseFactory
import com.example.db.extension.toHall
import com.example.db.extension.toSeat
import com.example.db.schemas.HallTable
import com.example.db.schemas.SeatsTable
import com.example.db.model.Hall
import com.example.db.model.Seats
import com.example.request.hall.GetSeatsRequest
import com.example.request.hall.HallAddRequest
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class HallRepositoryImpl: HallRepository {
    override suspend fun add(hallAddRequest: HallAddRequest): Hall? {

        val statement = DatabaseFactory.dbQuery {
            HallTable.insert {
                it[hallName] = hallAddRequest.hallName
                it[backgroundPath] = hallAddRequest.backgroundPath
            }
        }
        return statement.resultedValues?.first().toHall()
    }

    override suspend fun getAll(): List<Hall> = DatabaseFactory.dbQuery{
        HallTable
            .selectAll()
            .mapNotNull{ it.toHall()}
    }

    override suspend fun getAllSeats(getSeatsRequest: GetSeatsRequest): List<Seats>? {
        val hall = DatabaseFactory.dbQuery {
            HallTable.select(HallTable.hallName.eq(getSeatsRequest.hallName)).firstOrNull()
        } ?: return null

        val toHall = hall.toHall() ?: return null;

        return DatabaseFactory.dbQuery {
            SeatsTable.select(SeatsTable.hallName.eq(toHall.hallName)).mapNotNull { it.toSeat() }
        }
    }
}