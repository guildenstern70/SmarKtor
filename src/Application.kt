/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2020.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor

import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.features.*
import io.ktor.freemarker.*
import io.ktor.gson.*
import io.ktor.routing.*

@Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    /**
     * FreeMarker support
     */
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    /**
     * Default Headers
     */
    install(DefaultHeaders)

    /**
     * Logging of HTTP calls
     */
    install(CallLogging)

    /**
     * GSON support
     */
    install(ContentNegotiation) {
        gson {
        }
    }

    /**
     * HTTP Server
     */
    val client = HttpClient(Apache) {
    }

    /**
     * Routing
     */
    routing {
        root()
    }
}


