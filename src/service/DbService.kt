/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor.service

import net.littlelite.smarktor.config.Config
import net.littlelite.smarktor.dao.UserDao
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

        logger.info("Validating DB...")
        transaction {
            val user = UserDao.findByUsername("alessio")
            if (user!!.id._value == 1) {
                logger.info("DB is valid")
            } else {
                logger.warn("Unknown DB state")
            }
        }

    }
}