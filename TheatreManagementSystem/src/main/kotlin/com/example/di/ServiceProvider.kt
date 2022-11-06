package com.example.di


import com.example.data.repository.UserRepositoryImpl
import com.example.data.repository.seance.SeanceRepositoryImpl
import com.example.data.service.seance.SeanceService
import com.example.data.service.seance.SeanceServiceImpl
import com.example.data.service.user.UserService
import com.example.data.service.user.UserServiceImpl

object ServiceProvider {
    fun provideSeanceService(): SeanceService = SeanceServiceImpl(SeanceRepositoryImpl())
    fun provideUserService(): UserService = UserServiceImpl(UserRepositoryImpl())
}