/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor.controller

import io.ktor.application.*
import io.ktor.freemarker.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.web() {
    get("/") {
        call.respondRedirect("/index")
    }

    get("/index") {
        val context = mapOf("version" to "0.1.0")
        call.respond(FreeMarkerContent("index.ftl", context, ""))
    }

    static("/static") {
        resources("static")
    }

}