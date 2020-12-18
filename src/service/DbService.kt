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

        logger.info("Inserting users...")
        transaction {
            drop(Users)
            create(Users)
            UserService.addTestUsers()
        }
        logger.info("Done.")
    }
}