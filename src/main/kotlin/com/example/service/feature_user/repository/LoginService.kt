package com.example.service.feature_user.repository

import com.example.service.feature_user.model.User

interface LoginService {

    fun login(
        user: User,
        audience:String,
        issuer:String,
        secret:String,
        myRealm:String
    ):HashMap<String, String>

}