/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2020.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor.dto

import net.littlelite.smarktor.model.Users
import org.apache.commons.codec.digest.DigestUtils
import org.jetbrains.exposed.sql.insert

data class UserDTO(
    val id: Int,
    val username: String,
    val name: String,
    val surname: String,
    var initialPassword: String?,
    val age: Int
)
{
    fun insert()
    {
        val initPwd = initialPassword ?: "initialPassword"
        Users.insert {
            it[username] = username
            it[name] = name
            it[surname] = surname
            it[age] = age
            it[created] = System.currentTimeMillis()
            it[password] = passwordHash(initPwd)
        }
    }

    companion object
    {
        fun passwordHash(passwordInClear: String): String = DigestUtils.sha256Hex(passwordInClear)
        fun create(username: String, name: String, surname: String, age: Int) = UserDTO(
            -1, username, name, surname, null, age
        )
    }

}
