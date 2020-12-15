/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2020.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor

import io.ktor.application.*
import io.ktor.server.testing.*
import net.littlelite.smarktor.dao.UserRow
import net.littlelite.smarktor.dao.UsersDao
import net.littlelite.smarktor.service.DbService
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.exposed.sql.SizedIterable
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
            var userRecords: SizedIterable<UserRow>? = null
            transaction {
                userRecords = UsersDao.findByUsername("alessio")
                assertThat(userRecords!!.count()).isEqualTo(1L)
                val user = userRecords!!.iterator().next()
                assertThat(user.fullname).isEqualTo("Alessio Saltarin")
            }

        }
    }
}