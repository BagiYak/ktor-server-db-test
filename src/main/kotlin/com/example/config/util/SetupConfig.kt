package com.example.config.util

import com.example.config.AppConfig
import com.example.config.model.DatabaseConfig
import com.example.config.model.ServerConfig
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.setupConfig() {

    val appConfig by inject<AppConfig>()

    // Server
    val serverObject = environment.config.config("ktor.server")
    val isProd = serverObject.property("isProd").getString().toBoolean()
    appConfig.serverConfig = ServerConfig(isProd)

    // Database
    val databaseObject = environment.config.config("ktor.database")
    val driverClass = databaseObject.property("driverClass").getString()
    val url = databaseObject.property("url").getString()
    val user = databaseObject.property("user").getString()
    val password = databaseObject.property("password").getString()
    val maxConnections = databaseObject.property("max_connections").getString().toInt()
    appConfig.databaseConfig = DatabaseConfig(driverClass, url, user, password, maxConnections)

}