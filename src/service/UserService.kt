/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor.service

import net.littlelite.smarktor.dao.User
import net.littlelite.smarktor.dao.UserDao
import net.littlelite.smarktor.dto.UserDTO
import org.jetbrains.exposed.sql.transactions.transaction

object UserService
{
    fun getAllUsers(): List<UserDTO> = transaction {
            User.all().map( UserDTO::fromUser )
    }

    fun getUser(username: String): UserDTO? {
        val user = UserDao.findByUsername(username) ?: return null
        return UserDTO.fromUser(user)
    }

    fun deleteUser(userId: Int) {
        return UserDao.delete(userId)
    }

    fun addTestUsers() {
        getTestUsers().forEach{ user -> addUser(user) }
    }

    fun addUser(userDTO: UserDTO) = transaction {
        User.new {
            username = userDTO.username
            name = userDTO.name
            surname = userDTO.surname
            age = userDTO.age
            created = System.currentTimeMillis()
            password = userDTO.initialPassword ?: "initialPassword"
        }
    }

    private fun getTestUsers(): List<UserDTO>
    {
        val user1 = UserDTO.create("alessio",
            "Alessio",
            "Saltarin",
            50)

        val user2 = UserDTO.create("elena",
            "Elena",
            "Zambrelli",
            23)

        val user3 = UserDTO.create("raoul",
            "Raoul",
            "Invernizzi",
            33)

        return listOf(user1, user2, user3)
    }
}