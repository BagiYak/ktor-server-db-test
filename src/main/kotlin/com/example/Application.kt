package com.example

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.controller.routes.api.v1.loginRoutes
import com.example.controller.routes.public.helloWorldRouting
import com.example.di.loginModule
import com.example.plugins.configureSerialization
import com.example.plugins.cors
import com.example.plugins.logs
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import org.koin.ktor.ext.Koin


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    logs()

    // CORS configurations
    cors()

    // to accept application/json contentType request via http
    configureSerialization()

    // hello world - route to test public http
    helloWorldRouting()

    // Declare Koin
    install(Koin) {
        modules(loginModule)
    }

    // login feature - Configure JWT settings (a custom jwt group in the application.conf)
    val secret = environment.config.property("jwt.secret").getString()
    val issuer = environment.config.property("jwt.issuer").getString()
    val audience = environment.config.property("jwt.audience").getString()
    val myRealm = environment.config.property("jwt.realm").getString()

    install(Authentication) {

        // JWT auth
        jwt("auth-jwt") {

            // The realm property allows you to set the realm to be passed in WWW-Authenticate header when accessing a protected route
            realm = myRealm

            // The verifier function allows you to verify a token format and its signature: HS256, you need to pass a JWTVerifier instance to verify a token
            verifier(
                JWT
                    .require(Algorithm.HMAC256(secret))
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build()
            )

            // The validate function allows you to perform additional validations on the JWT payload in the following way
            validate { credential ->

                log.info("START validate credential")

                val email = credential.payload.getClaim("email").asString()

                // Check the credential parameter, which represents a JWTCredential object and contains the JWT payload. In the example below, the value of a custom email claim is checked.
                if (email != "") {
                    log.info("Ok: validate credential email: $email")
                    // In a case of successful authentication, return JWTPrincipal. If authentication fails, return null
                    JWTPrincipal(credential.payload)
                } else {
                    log.info("Bad: validate credential email: typed wrong $email")
                    null
                }
            }
        }
    }

    // login feature - route
    loginRoutes(audience, issuer, secret, myRealm)

}
