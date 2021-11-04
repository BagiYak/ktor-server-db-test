package com.example.controller.routes.public

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.helloWorldRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}