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

    get("users/{username}") {
        val user = call.parameters["username"] ?: throw BadRequestException("username")
        val theUser = UserService.getUser(user) ?: throw NotFoundException()
        call.respond(theUser)
    }

    post("users") {
        val userRequest = call.receive<UserDTO>()
        UserService.addUser(userRequest)
        call.respond(HttpStatusCode.Accepted)
    }

    delete("users/{id}") {
        val userId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        UserService.deleteUser(userId)
        call.respond(HttpStatusCode.OK)
    }
}