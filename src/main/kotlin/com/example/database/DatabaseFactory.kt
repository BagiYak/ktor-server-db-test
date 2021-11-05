package com.example.database

interface DatabaseFactory {
    fun connect()
    fun close()
}