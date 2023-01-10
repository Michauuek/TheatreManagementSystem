package com.example.db

import com.example.db.schemas.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    private const val USE_POSTGRES = true;

    //TODO Create Database if not exists (with another connection);

    fun init(){
        val dataSource = if(USE_POSTGRES) { postgresConnection() } else { h2Connection() };

        val db = Database.connect(dataSource)

        val connected = transaction {
                try {
                    !connection.isClosed
                } catch (e: Exception) {
                    false
                }
            }

        if(!connected) {
            println("FAILED TO CONNECT TO DATABASE");

            throw Exception("FAILED TO CONNECT TO DATABASE");
        }

        transaction {
            SchemaUtils.create(HallTable)
            SchemaUtils.create(ActorTable)
            SchemaUtils.create(CastTable)
            SchemaUtils.create(SeanceTable)
            SchemaUtils.create(OauthWhiteListTable)
            SchemaUtils.create(ReservationTable)
            SchemaUtils.create(SeatsTable)
            SchemaUtils.create(PerformanceTable)
            SchemaUtils.create(ReservedSeatsTable)
        }
    }

    private fun h2Connection(): HikariDataSource {
        val config = HikariConfig()

        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:file:./build/db"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()

        return HikariDataSource(config);
    }

    private fun postgresConnection(): HikariDataSource{
        val config = HikariConfig()

        config.driverClassName = "org.postgresql.Driver"
        //config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:postgresql:theatredb?user=postgres&password=root"
        //config.jdbcUrl = "jdbc:h2:mem:test"
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