package com.example.data.db

import com.example.data.db.schemas.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init(){
        //Database.connect(hikariConnection())
        val driverClassName = "org.h2.Driver"
        val jdbcURL = "jdbc:h2:file:./build/db"
        val database = Database.connect(jdbcURL, driverClassName)
        transaction {
            SchemaUtils.create(HallTable)
            SchemaUtils.create(ActorTable)
            SchemaUtils.create(CastTable)
            SchemaUtils.create(SeanceTable)
            SchemaUtils.create(UserTable)
            SchemaUtils.create(ReservationTable)
            SchemaUtils.create(SeatsTable)
            SchemaUtils.create(PerformanceTable)
        }
    }

    private fun hikariConnection(): HikariDataSource{
        val config = HikariConfig()

        config.driverClassName = "org.postgresql.Driver"
        config.jdbcUrl = "jdbc:postgresql:theatredb?user=postgres&password=root"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()

        return HikariDataSource(config)
    }

    //Function allows performing SQL queries on db
    suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
        transaction {
            block()
        }
    }
}