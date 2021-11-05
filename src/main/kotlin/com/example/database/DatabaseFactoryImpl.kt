package com.example.database

import com.example.config.AppConfig
import org.jetbrains.exposed.sql.Database

class DatabaseFactoryImpl(appConfig: AppConfig) : DatabaseFactory {

    private val dbConfig = appConfig.databaseConfig

    override fun close() {
        // not necessary
    }

    override fun connect() {
        postgresql()
    }

    private fun postgresql(): Database {

        // "jdbc:postgresql://localhost:5432/test_db", user = "postgres", password = "postgres"
        // Exposed framework connection = Database.connect("jdbc:postgresql://localhost:5432/test_db", user = "postgres", password = "postgres")
        return Database.connect(
            url = dbConfig.url,
            user = dbConfig.user,
            password = dbConfig.password
        )

    }
}