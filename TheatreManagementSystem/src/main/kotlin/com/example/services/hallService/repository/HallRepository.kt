package com.example.services.hallService.repository

import com.example.db.DatabaseFactory
import com.example.db.extension.toHall
import com.example.db.extension.toSeat
import com.example.db.extension.toSimpleSeat
import com.example.db.schemas.HallTable
import com.example.db.schemas.SeatsTable
import com.example.db.model.Hall
import com.example.db.model.Seats
import com.example.request.hall.GetSeatsRequest
import com.example.request.hall.HallAddRequest
import com.example.response.hall.SeatsResponse
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class HallRepository {
    suspend fun add(hallAddRequest: HallAddRequest): Hall? {

        val statement = DatabaseFactory.dbQuery {
            HallTable.insert {
                it[hallName] = hallAddRequest.hallName
                it[backgroundPath] = hallAddRequest.backgroundPath
            }
        }
        return statement.resultedValues?.first().toHall()
    }

    suspend fun getHall(hallName: String): Hall? = DatabaseFactory.dbQuery {
        HallTable.select {
            HallTable.hallName eq hallName
        }.firstOrNull()?.toHall()
    }

    suspend fun getAll(): List<Hall> = DatabaseFactory.dbQuery{
        HallTable
            .selectAll()
            .mapNotNull{ it.toHall()}
    }

    suspend fun getSeatsFromHall(hallName: String): SeatsResponse {
        val targetHall = getHall(hallName) ?: return SeatsResponse("Unknown Hall", listOf());

        val seats = DatabaseFactory.dbQuery {
            SeatsTable.select(SeatsTable.hallName.eq(targetHall.hallName)).mapNotNull { it.toSimpleSeat() }
        }

        return SeatsResponse(targetHall.hallName, seats)
    }
}