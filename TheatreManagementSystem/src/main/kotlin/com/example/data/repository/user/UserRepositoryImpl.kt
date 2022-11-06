package com.example.data.repository

import com.example.data.db.DatabaseFactory
import com.example.data.db.extension.toUser
import com.example.data.db.schemas.UserTable
import com.example.data.model.User
import com.example.data.request.UserLoginRequest
import com.example.data.request.UserRequest
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserRepositoryImpl: UserRepository {
    override suspend fun registerUser(userRequest: UserRequest): User? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = UserTable.insert {
                it[email] = userRequest.email
                it[password] = userRequest.password
            }
        }
        return statement?.resultedValues?.first().toUser()
    }

    override suspend fun loginUser(userLoginRequest: UserLoginRequest): User? {
        TODO("Implement user login request")
    }

    override suspend fun getByEmail(email: String): User? {
        val user = DatabaseFactory.dbQuery {
            UserTable.select{
                UserTable.email.eq(email)
            }.map { it.toUser() }.singleOrNull()
        }
        return user
    }
}