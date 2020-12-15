/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2020.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor.logic

import net.littlelite.smarktor.model.Users
import org.apache.commons.codec.digest.DigestUtils
import org.jetbrains.exposed.sql.insert

data class User(
    val username: String,
    val clearPassword: String,
    val name: String,
    val surname: String
)
{
    private val passwordHash = DigestUtils.sha256Hex(this.clearPassword)

    fun insert()
    {
        val userappname = this.username
        Users.insert {
            it[username] = userappname
            it[fullname] = "$name $surname"
            it[created] = System.currentTimeMillis()
            it[password] = passwordHash
        }
    }


}
