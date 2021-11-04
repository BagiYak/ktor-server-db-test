package com.example.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*

fun Application.cors() {

    // https://ktor.io/docs/cors.html
    install(CORS) {

        // Allows cross-origin requests from any host
        anyHost()

        header(HttpHeaders.Authorization)

        // Send credential information (such as cookies or authentication information) with cross-origin requests
        allowCredentials = true

        // Specify how long the response to the preflight request can be cached without sending another preflight request.
        // Duration in seconds to tell the client to keep the host in a list of known HSTS hosts.
        maxAgeInSeconds = 3600
    }

}
