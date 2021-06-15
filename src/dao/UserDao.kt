/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor.dao

import net.littlelite.smarktor.model.Users
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction

object UserDao
{
    fun findByAgeGreaterThan(age: Int): SizedIterable<User>
    {
        return User.find { Users.age greaterEq age }
    }

    fun findByUsername(username: String): User?
    {
        val users: SizedIterable<User> = User.find { Users.username eq username }
        if (users.empty())
            return null
        return users.first()
    }

    fun delete(userId: Int) = transaction {
        User[userId].delete()
    }
}