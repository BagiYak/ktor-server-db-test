package com.example.controller.routes.api.v1

import com.example.service.feature_user.model.User
import com.example.service.feature_user.usecase.UserUseCases
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Application.loginRoutes(
    audience:String,
    issuer:String,
    secret:String,
    myRealm:String
) {

    // lazy inject LoginService
    val userUseCases by inject<UserUseCases>()

    routing {

        // to handle user login with JWT, defines an authentication route for receiving POST requests
        post("/api/v1/login/user") {

            log.info("START Login")

            // receives user credentials sent as a JSON object and converts it to a User class object
            val user = call.receive<User>()

            // send json response with Token or Bad message
            call.respond(
                message = userUseCases.login(user, audience, issuer, secret, myRealm)
            )

            log.info("END Login")
        }

        // define the authorization for the JWT resources in our application using the authenticate function
        authenticate("auth-jwt") {

            // to handle login with JWT token. In a case of successful authentication, you can retrieve an authenticated JWTPrincipal inside a route handler using the call.principal function
            get("/user") {

                val principal: JWTPrincipal? = call.principal()

                if(principal != null) {
                    // the value of a custom email claim
                    val email = principal.payload.getClaim("email").asString()
                    // and a token expiration time are retrieved
                    val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())

                    log.info("Ok: /user auth-jwt principal")
                    call.respondText("Hello user, $email! Token is expired at $expiresAt ms.")
                } else {
                    log.info("Bad: /user auth-jwt principal: is $principal")
                    call.respondRedirect("/")
                }
            }
        }
    }

}