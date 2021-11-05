package com.example.service.feature_user.model

import com.example.util.Roles
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val password: String,
    val token: String? = null,
    val firstname: String? = null,
    val lastname: String? = null,
    val age: Int? = null,
    val gender: String? = null,
    val address: String? = null,
    val picture_url: String? = null,
    val phone: Long? = null,
    val date_created: Long? = null,
    val date_logged_in: Long? = null,
    val roles: Set<Roles>? = null,  // setOf(Roles.USER)
)