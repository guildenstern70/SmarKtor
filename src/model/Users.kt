/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2020.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor.model

import org.jetbrains.exposed.dao.id.IntIdTable

object Users : IntIdTable() {
    val username = varchar("username",128)
    var password = varchar("password",255)
    val fullname = varchar("fullname",128)
    val created = long("created")
    override val primaryKey = PrimaryKey(id)
}
