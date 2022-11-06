package com.example.data.service.user

import com.example.data.model.User
import com.example.data.repository.UserRepository
import com.example.data.request.UserRequest
import com.example.data.service.user.UserService
import io.ktor.features.*
import io.ktor.utils.io.errors.*

class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {

    override suspend fun getByEmail(email: String): User {
        val user = userRepository.getByEmail(email)
        return user ?: throw NotFoundException("User not found")
    }

    override suspend fun registerUser(userRequest: UserRequest): User {
        return userRepository.registerUser(userRequest) ?: throw IOException("User cannot be register.")
        TODO("Check if email is not taken")
    }
}