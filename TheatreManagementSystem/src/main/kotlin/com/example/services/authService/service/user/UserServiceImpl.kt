package com.example.services.authService.service.user

import com.example.db.model.User
import com.example.data.repository.UserRepository

import io.ktor.server.plugins.*
import io.ktor.utils.io.errors.*

class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {

    override suspend fun getByEmail(email: String): User {
        val user = userRepository.getByEmail(email)
        return user ?: throw NotFoundException("User not found")
    }

    override suspend fun register(userRequest: UserRequest): User {
        return userRepository.registerUser(userRequest) ?: throw IOException("User cannot be register.")
        TODO("Check if email is not taken")
    }
}