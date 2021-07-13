/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor.controller.routes

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import net.littlelite.smarktor.dto.UserDTO
import net.littlelite.smarktor.service.UserService

fun Route.users() {

    get("users") {
        val allUsers: List<UserDTO> = UserService.getAllUsers()
        call.respond(allUsers)
    }

    get("users/{id}") {
        val idString = call.parameters["id"] ?: return@get call.respondText(
            "Missing or malformed id",
            status = HttpStatusCode.BadRequest
        )
        val theUser = UserService.getUser(idString.toInt()) ?: throw NotFoundException()
        call.respond(theUser)
    }

    get("users/byusername/{username}") {
        val user = call.parameters["username"] ?: return@get call.respondText(
            "Missing or malformed username",
            status = HttpStatusCode.BadRequest
        )
        val theUser = UserService.getUserByUsername(user) ?: throw NotFoundException()
        call.respond(theUser)
    }

    post("users") {
        val userRequest = call.receive<UserDTO>()
        UserService.addUser(userRequest)
        call.respond(HttpStatusCode.Created)
    }

    delete("users/{id}") {
        val userId = call.parameters["id"] ?: return@delete call.respondText(
            "Missing or malformed id",
            status = HttpStatusCode.BadRequest
        )
        UserService.deleteUser(userId.toInt())
        call.respond(HttpStatusCode.OK)
    }
}