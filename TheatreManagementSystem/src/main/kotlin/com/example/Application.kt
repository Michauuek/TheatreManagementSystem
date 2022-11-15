package com.example

import com.example.config.AppConfiguration
import com.example.config.AppConfiguration.configureContentNegotiation
import com.example.config.AppConfiguration.configureCors
import com.example.config.AppConfiguration.configureDatabase
import com.example.config.AppConfiguration.configureRouting
import com.example.config.AppExceptionsConfiguration.configureExceptions
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureContentNegotiation()
        configureCors()
        configureRouting()
        configureDatabase()
        configureExceptions()
    }.start(wait = true)
}
