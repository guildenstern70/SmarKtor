/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2020.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor.controller

import io.ktor.routing.*

fun Routing.api() {

    route("/api/v1") {
        users()
    }

}