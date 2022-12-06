package com.example.services.authService


import com.example.db.DatabaseFactory
import io.ktor.server.engine.*
import io.ktor.server.netty.*

const val AUTH_SERVICE_PORT = 8081
const val AUTH_SERVICE_NAME = "auth-service"

fun main(args: Array<String>) {
    embeddedServer(Netty, port = AUTH_SERVICE_PORT)
    {
        DatabaseFactory.init()

    }.start(wait = true)
}

