package com.automobile.plugins

import com.automobile.routes.getCar
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        getCar()
        // Static plugin. Try to access `/static/index.html`
        static {
            resources("static")
        }
    }
}
