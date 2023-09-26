package com.automobile.plugins

import com.automobile.routing.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText { "Hello world!" }
        }
    }

    accountRoutes()
    bonusPointsTransactionRoutes()
    carAvailabilityRoutes()
    carReservationRoutes()
    carRoutes()
    paymentRoutes()
    userProfileRoutes()
}


