package com.example.database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object RolesTable : IntIdTable() {

    override val primaryKey = PrimaryKey(id, name = "role_id")
//    val role_id: Column<Int> = integer("role_id").uniqueIndex()
    val role_name: Column<String> = varchar("role_name", 32).uniqueIndex()


}