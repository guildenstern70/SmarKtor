/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2021.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor.model

import org.jetbrains.exposed.dao.id.IntIdTable

object Users : IntIdTable() {
    val username = varchar("username",128).uniqueIndex("username_idx")
    var password = varchar("password",255)
    val name = varchar("name",128)
    val surname = varchar("surname",128)
    val age = integer("age")
    val created = long("created")
    override val primaryKey = PrimaryKey(id)
}
