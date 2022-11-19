package com.example.di


import com.example.data.model.Performance
import com.example.data.repository.actor.ActorRepositoryImpl
import com.example.data.repository.cast.CastRepositoryImpl
import com.example.data.repository.user.UserRepositoryImpl
import com.example.data.repository.hall.HallRepositoryImpl
import com.example.data.repository.performance.PerformanceRepositoryImpl
import com.example.data.repository.seance.SeanceRepositoryImpl
import com.example.data.service.actor.ActorService
import com.example.data.service.actor.ActorServiceImpl
import com.example.data.service.cast.CastService
import com.example.data.service.cast.CastServiceImpl
import com.example.data.service.hall.HallService
import com.example.data.service.hall.HallServiceImpl
import com.example.data.service.performance.PerformanceService
import com.example.data.service.performance.PerformanceServiceImpl
import com.example.data.service.seance.SeanceService
import com.example.data.service.seance.SeanceServiceImpl
import com.example.data.service.user.UserService
import com.example.data.service.user.UserServiceImpl

object ServiceProvider {
    fun provideSeanceService(): SeanceService = SeanceServiceImpl(SeanceRepositoryImpl())
    fun provideUserService(): UserService = UserServiceImpl(UserRepositoryImpl())
    fun provideHallService(): HallService = HallServiceImpl(HallRepositoryImpl())
    fun provideActorService(): ActorService = ActorServiceImpl(ActorRepositoryImpl())
    fun provideCastService(): CastService = CastServiceImpl(CastRepositoryImpl(), ActorRepositoryImpl())
    fun providePerformanceService(): PerformanceService = PerformanceServiceImpl(PerformanceRepositoryImpl())
}