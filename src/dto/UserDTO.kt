/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor.dto

import net.littlelite.smarktor.dao.User
import org.apache.commons.codec.digest.DigestUtils
import org.jetbrains.exposed.sql.transactions.transaction

data class UserDTO(
    val id: Int,
    val username: String,
    val name: String,
    val surname: String,
    var initialPassword: String?,
    val age: Int
)
{
    fun insert() = transaction {
        val initPwd = initialPassword ?: "initialPassword"
        User.new {
            username = this.username
            name = this.name
            surname = this.surname
            age = this.age
            created = System.currentTimeMillis()
            password = passwordHash(initPwd)
        }
    }

    companion object
    {
        fun passwordHash(passwordInClear: String): String =
            DigestUtils.sha256Hex(passwordInClear)

        fun create(username: String, name: String, surname: String, age: Int) = UserDTO(
            -1, username, name, surname, null, age
        )

        fun fromUser(user: User) =  UserDTO(
            user.id.value, user.username, user.name, user.surname, "****", user.age
        )
    }

}
