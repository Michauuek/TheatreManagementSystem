package com.example.data.repository.hall

import com.example.data.db.DatabaseFactory
import com.example.data.db.extension.toHall
import com.example.data.db.extension.toSeat
import com.example.data.db.schemas.HallTable
import com.example.data.db.schemas.SeatsTable
import com.example.data.model.Hall
import com.example.data.model.Seats
import com.example.data.request.GetSeatsRequest
import com.example.data.request.HallRequest
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class HallRepositoryImpl: HallRepository {
    override suspend fun add(hallRequest: HallRequest): Hall? {

        var statement = DatabaseFactory.dbQuery {
            HallTable.insert {
                it[hallName] = hallRequest.hallName
                it[backgroundPath] = hallRequest.backgroundPath
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