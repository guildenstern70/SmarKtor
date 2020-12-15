/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2020.
 * This software is licensed under MIT License.
 *
 */

val ktorVersion: String by extra("1.4.3")

plugins {
    application
    kotlin("jvm") version "1.4.21"
}

repositories {
    jcenter()
    mavenCentral()
    maven(url = "https://kotlin.bintray.com/ktor")
}

group "net.littlelite.smarktor"
version "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

application {
    mainClassName = "net.littlelite.smarktor.MainKt"
}

sourceSets {

    named("main") {
        resources.srcDirs("resources")
        withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
            kotlin.srcDirs("src")
        }
    }

    named("test") {
        withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
            kotlin.srcDirs("test")
        }
    }
}


dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:1.2.1")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-freemarker:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-client-apache:$ktorVersion")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}