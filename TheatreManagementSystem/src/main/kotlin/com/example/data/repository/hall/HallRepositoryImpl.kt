package com.example.data.repository.hall

import com.example.data.db.DatabaseFactory
import com.example.data.db.extension.toHall
import com.example.data.db.schemas.HallTable
import com.example.data.model.Hall
import com.example.data.request.HallRequest
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class HallRepositoryImpl: HallRepository {
    override suspend fun add(hallRequest: HallRequest): Hall? {
        // dbQuerry zwraca wartość więc nie trzeba statement robić

        //var statement: InsertStatement<Number>? = null

        var statement = DatabaseFactory.dbQuery {
            HallTable.insert {
                it[hallName] = hallRequest.hallName
                it[seatsLayout] = hallRequest.seatsLayout
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
}