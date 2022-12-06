package com.example.services.authService.repository.user

import com.example.db.DatabaseFactory
import com.example.db.extension.toUser
import com.example.db.schemas.UserTable
import com.example.db.model.User
import com.example.data.repository.UserRepository
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserRepositoryImpl: UserRepository {
//    override suspend fun registerUser(userRequest: UserRequest): User? {
//        var statement: InsertStatement<Number>? = null
//        DatabaseFactory.dbQuery {
//            statement = UserTable.insert {
//                it[email] = userRequest.email
//                it[password] = userRequest.password
//            }
//        }
//        return statement?.resultedValues?.first().toUser()
//    }
//
//    override suspend fun loginUser(userLoginRequest: UserLoginRequest): User? {
//        TODO("Implement user login request")
//    }
//
//    override suspend fun getByEmail(email: String): User? {
//        val user = DatabaseFactory.dbQuery {
//            UserTable.select{
//                UserTable.email.eq(email)
//            }.map { it.toUser() }.singleOrNull()
//        }
//        return user
//    }
}