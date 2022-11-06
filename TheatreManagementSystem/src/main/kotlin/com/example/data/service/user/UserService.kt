package com.example.data.service.user

import com.example.data.model.User
import com.example.data.request.UserRequest

interface UserService {
    suspend fun getByEmail(email: String): User
    suspend fun register(userRequest: UserRequest): User
}