/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor

import io.ktor.application.*
import io.ktor.server.testing.*
import net.littlelite.smarktor.dao.UserDao
import net.littlelite.smarktor.service.DbService
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.AfterClass
import org.junit.Test

class DatabaseTest
{
    companion object
    {
        @AfterClass
        fun cleanupDb()
        {
            DbService.destroy()
        }
    }

    @Test
    fun dbTest()
    {
        withTestApplication({
            DbService.init(log)
            module(testing = true)
        })
        {
            transaction {
                val user = UserDao.findByUsername("alessio")
                assertThat(user).isNotNull
                assertThat(user!!.name).isEqualTo("Alessio")
                assertThat(user.surname).isEqualTo("Saltarin")
            }
        }
    }
}