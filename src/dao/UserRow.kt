/*
 * Project SmarKtor
 *
 * A template project for Ktor in Kotlin
 *
 * Copyright (c) Alessio Saltarin, 2020.
 * This software is licensed under MIT License.
 *
 */

package net.littlelite.smarktor.dao

import net.littlelite.smarktor.model.Users
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID


class UserRow(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<UserRow>(Users)
    var username by Users.username
    var password by Users.password
    var fullname by Users.fullname
    var created by Users.created
}
