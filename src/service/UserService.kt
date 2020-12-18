/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2020.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor.service

import net.littlelite.smarktor.dao.UserEntity
import net.littlelite.smarktor.dto.UserDTO
import net.littlelite.smarktor.model.Users
import org.jetbrains.exposed.sql.transactions.transaction

object UserService
{
    fun getAllUsers(): List<UserDTO> = transaction {
            UserEntity.all().map(UserEntity::toUser)
    }

    fun getUser(userId: Int): UserDTO?
    {
        var user: UserDTO? = null
        transaction {
            val userEntity = UserEntity.findById(userId) ?: null
            if (userEntity != null)
            {
                user = userEntity.toUser()
            }
        }
        return user
    }

    fun getUser(username: String): UserDTO?
    {
        val users = UserEntity.find { Users.username eq username }
        if (users.empty())
        {
            return null
        }
        return users.iterator().next().toUser()
    }

    fun deleteUser(userId: Int) = transaction {
        UserEntity[userId].delete()
    }

    fun addTestUsers() {
        getTestUsers().forEach{ user -> addUser(user) }
    }

    fun addUser(userDTO: UserDTO) = transaction {
        UserEntity.new {
            this.username = userDTO.username
            this.name = userDTO.name
            this.surname = userDTO.surname
            this.age = userDTO.age
            this.created = System.currentTimeMillis()
            this.password = userDTO.initialPassword ?: "initialPassword"
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