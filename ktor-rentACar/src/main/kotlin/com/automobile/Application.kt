package com.automobile

import com.automobile.data.model.Car
import com.automobile.entities.CarEntity
import com.automobile.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.insert
import org.ktorm.dsl.select

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        val database = Database.connect(
            url = "jdbc:mysql://localhost:3306/automobile",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root"
        )

        val getCar = database.from(CarEntity)
            .select()
            for (row in getCar) {
                println("${row[CarEntity.carID]}: ${row[CarEntity.carBrand]}")
            }

    }.start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureTemplating()
    configureMonitoring()
    configureRouting()
}
