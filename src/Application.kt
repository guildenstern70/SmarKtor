/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2021.
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
import net.littlelite.smarktor.controller.api
import net.littlelite.smarktor.controller.web
import net.littlelite.smarktor.service.DbService
import org.slf4j.event.Level


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
    install(CallLogging) {
        level = Level.INFO
    }

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
     * Initialize DB
     */
    DbService.init(log)

    /**
     * Routing
     */
    routing {
        web()
        api()
    }

}


