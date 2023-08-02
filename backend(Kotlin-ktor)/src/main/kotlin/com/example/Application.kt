package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.routes.accountRoutes
import com.example.routes.bankRoutes
import com.example.routes.customerRoutes
import com.example.routes.transactionRoutes

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureHTTP()
    configureSecurity()
    configureRouting()
    customerRoutes()
    bankRoutes()
    accountRoutes()
    transactionRoutes()
}
