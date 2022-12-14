package com.example

import com.example.config.configureBasicCors
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost", module = Application::myApplicationModule).start(wait = true)
}
fun Application.myApplicationModule() {
    configureBasicCors()

    routing {
        singlePageApplication {
            filesPath = "client/build"
        }
    }
}
