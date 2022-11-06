package com.example

import com.example.config.AppConfiguration
import com.example.config.AppConfiguration.configureContentNegotiation
import com.example.config.AppConfiguration.configureCors
import com.example.config.AppConfiguration.configureRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureContentNegotiation()
        configureCors()
        configureRouting()
        AppConfiguration.configureDatabase()
    }.start(wait = true)
}
