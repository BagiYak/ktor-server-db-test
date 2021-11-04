package com.example.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import org.slf4j.event.*

fun Application.logs() {

    install(CallLogging) {
        level = Level.INFO
        filter { call ->
            call.request.path().startsWith("/api/v1")
        }
        format { call ->
            val status = call.response.status()
            val httpMethod = call.request.httpMethod.value
            val userAgent = call.request.headers["User-Agent"]
            "Status: $status, HTTP method: $httpMethod, User agent: $userAgent"
        }
    }
}