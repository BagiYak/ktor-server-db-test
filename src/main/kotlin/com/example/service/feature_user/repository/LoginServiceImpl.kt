package com.example.service.feature_user.repository

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.service.feature_user.model.User
import java.util.*

class LoginServiceImpl() : LoginService {

    override fun login(
        user: User,
        audience:String,
        issuer:String,
        secret:String,
        myRealm:String
    ): HashMap<String, String> {

        // generates a token with the specified JWT settings, adds a custom claim with a received username, and signs a token with the specified algorithm: HS256, a shared secret is used to sign a token
        val token = JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("email", user.email)
            // set token expire time to 3 minutes
            .withExpiresAt(Date(System.currentTimeMillis() + 604800000))    // 7 days token expire
            .sign(Algorithm.HMAC256(secret))

        return hashMapOf(
            "token" to token
        )

    }

}