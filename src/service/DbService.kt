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

import net.littlelite.smarktor.config.Config
import net.littlelite.smarktor.logic.User
import net.littlelite.smarktor.model.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.drop
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.Logger

object DbService
{

    fun destroy() {
        transaction {
            drop(Users)
        }
    }

    fun init(logger: Logger) {

        logger.info("Connecting to database...")
        Database.connect(Config.hikariDataSource())
        logger.info("Done.")

        val user1 = User("alessio",
            "doctor",
            "Alessio",
        "Saltarin")

        val user2 = User("elena",
            "elena",
            "Elena",
            "Zambrelli")

        logger.info("Inserting users...")
        transaction {
            create(Users)
            user1.insert()
            user2.insert()
        }
        logger.info("Done.")
    }
}