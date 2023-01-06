package com.example.services.authService.service

import com.example.auth.GoogleUserInfo
import com.example.response.auth.Privilege
import com.example.services.authService.repository.AuthRepository
class AuthService (
    private val authRepository: AuthRepository,
) {
    suspend fun getPrivilege(userSession: GoogleUserInfo?): Privilege {
        return authRepository.getPrivilege(userSession);
    }

}