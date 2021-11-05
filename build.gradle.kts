val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val koinVersion: String by project
val exposedVersion: String by project
val postgresqlDriverVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.5.31"
                id("org.jetbrains.kotlin.plugin.serialization") version "1.5.31"
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("com.example.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

    // server
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")

    // serialization
    implementation("io.ktor:ktor-serialization:$ktorVersion")

    // auth & JWT
    implementation("io.ktor:ktor-auth:$ktorVersion")
    implementation("io.ktor:ktor-auth-jwt:$ktorVersion")

    // DataBase driver
    implementation("org.postgresql:postgresql:$postgresqlDriverVersion")  // Postgresql driver

    // Exposed framework for DB
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")

    // log & test
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")

    // Koin (dependency injection) for Kotlin apps
    implementation("io.insert-koin:koin-ktor:$koinVersion")
}