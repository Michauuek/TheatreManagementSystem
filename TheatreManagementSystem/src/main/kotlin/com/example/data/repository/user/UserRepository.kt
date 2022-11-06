package com.example.data.repository

import com.example.data.model.User
import com.example.data.request.UserLoginRequest
import com.example.data.request.UserRequest

interface UserRepository {
    suspend fun registerUser(userRequest: UserRequest): User?
    suspend fun loginUser(userLoginRequest: UserLoginRequest): User?
    suspend fun getByEmail(email: String): User?
}