package com.example.di


import com.example.data.repository.user.UserRepositoryImpl
import com.example.data.repository.hall.HallRepositoryImpl
import com.example.data.repository.schedule.ScheduleRepositoryImpl
import com.example.data.repository.seance.SeanceRepositoryImpl
import com.example.data.service.hall.HallService
import com.example.data.service.hall.HallServiceImpl
import com.example.data.service.schedule.ScheduleService
import com.example.data.service.schedule.ScheduleServiceImpl
import com.example.data.service.seance.SeanceService
import com.example.data.service.seance.SeanceServiceImpl
import com.example.data.service.user.UserService
import com.example.data.service.user.UserServiceImpl

object ServiceProvider {
    fun provideSeanceService(): SeanceService = SeanceServiceImpl(SeanceRepositoryImpl())
    fun provideUserService(): UserService = UserServiceImpl(UserRepositoryImpl())
    fun provideHallService(): HallService = HallServiceImpl(HallRepositoryImpl())
    fun provideScheduleService(): ScheduleService = ScheduleServiceImpl(ScheduleRepositoryImpl())
}