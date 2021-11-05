package com.example.service.feature_user.usecase

import com.example.service.feature_user.model.User
import com.example.service.feature_user.repository.LoginService

class LoginUseCase(
    private val service: LoginService
) {

    operator fun invoke(
        user: User,
        audience:String,
        issuer:String,
        secret:String,
        myRealm:String
    ): HashMap<String, String> {

        // Check email and password if Bad return Bad token info
        if(user.email.isBlank() || !user.email.contains('@')) {
            return hashMapOf(
                "message" to "failed login: check email/password: wrong typo in email - '${user.email}'"
            )
        }

        // return generated token
        return service.login(user, audience, issuer, secret, myRealm)
    }
}