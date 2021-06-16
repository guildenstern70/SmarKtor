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
    fun findByAgeGreaterThan(age: Int): SizedIterable<User> = transaction {
        User.find { Users.age greaterEq age }
    }

    fun findById(id: Int): User? = transaction {
        User.findById(id)
    }

    fun findByUsername(username: String): User? {
        val users: SizedIterable<User> = transaction {
            User.find { Users.username eq username }
        }
        return transaction {
            if (users.empty())
                null
            else
                users.first()
        }
    }

    fun delete(userId: Int) = transaction {
        User[userId].delete()
    }
}