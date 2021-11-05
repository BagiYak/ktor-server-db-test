package com.example.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.`java-time`.timestamp
import java.time.Instant

object UsersTable : IntIdTable(name = "users") {

    val sequelId: Column<Long> = long("sequel_id").uniqueIndex()
    val email: Column<String> = varchar("email", 100)
    val password: Column<String> = varchar("password", 510)
    val firstname: Column<String> = varchar("firstname", 50)
    val lastname: Column<String> = varchar("lastname", 50)
    val age: Column<Int> = integer("age")
    val gender: Column<String> = varchar("gender", 20)
    val address: Column<String> = varchar("address", 255)
    val picture_url: Column<String> = varchar("picture_url", 255)
    val phone: Column<Long> = long ("phone")
    val date_created: Column<Instant> = timestamp ("date_created")
    val date_logged_in: Column<Instant> = timestamp ("date_logged_in")
}