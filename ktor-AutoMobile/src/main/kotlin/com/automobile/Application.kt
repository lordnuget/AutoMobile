package com.automobile

import com.automobile.data.models.Car
import com.automobile.database.DatabaseConnection
import com.automobile.entities.CarEntity
import com.automobile.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.css.select
import kotlinx.serialization.serializer
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        install(ContentNegotiation) {
            json()
        }
    }.start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureTemplating()
    configureMonitoring()
    configureRouting()
}
