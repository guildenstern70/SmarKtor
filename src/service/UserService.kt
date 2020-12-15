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

import net.littlelite.smarktor.logic.User

object UserService
{
    fun getTestUsers(): List<User>
    {
        val user1 = User("alessio",
            "doctor",
            "Alessio",
            "Saltarin")

        val user2 = User("elena",
            "elena",
            "Elena",
            "Zambrelli")

        val user3 = User("raoul",
            "raul",
            "Raoul",
            "Invernizzi")

        return listOf(user1, user2, user3)
    }
}