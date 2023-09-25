package com.automobile.plugins

import com.automobile.routing.carRoutes
import com.automobile.routing.userRoutes
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

    carRoutes()
    userRoutes()
}


