/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2020.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor.dao

import net.littlelite.smarktor.logic.User
import net.littlelite.smarktor.model.Users
import org.jetbrains.exposed.sql.SizedIterable


object UsersDao
{
    fun findAll() : SizedIterable<UserRow> = UserRow.all()
    fun findByUsername(username: String): SizedIterable<UserRow> = UserRow.find { Users.username eq username }

    fun checkPassword(userId: Int, passwordInClear: String) : Boolean
    {
        val user = UserRow.findById(userId) ?: return false
        val pwdHash = User.passwordHash(passwordInClear)
        return (pwdHash == user.password)
    }
}