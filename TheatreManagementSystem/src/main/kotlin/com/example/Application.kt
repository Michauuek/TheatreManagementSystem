package com.example

import com.example.config.AppConfiguration
import com.example.config.AppConfiguration.configureAuth
import com.example.config.AppConfiguration.configureContentNegotiation
import com.example.config.AppConfiguration.configureCors
import com.example.config.AppConfiguration.configureDatabase
import com.example.config.AppExceptionsConfiguration.configureExceptions
import io.ktor.server.engine.*
import io.ktor.server.netty.*

// TODO REMOVE THIS SERVICE OR REPURPOSE IT TO BE A GATEWAY / frontend service

fun main() {
//    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
//        configureContentNegotiation()
//        configureCors()
//        configureRouting()
//        configureDatabase()
//        configureExceptions()
//        configureAuth()
//    }.start(wait = true)
}
