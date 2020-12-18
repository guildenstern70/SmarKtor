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

import net.littlelite.smarktor.dto.UserDTO
import net.littlelite.smarktor.model.Users
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID


class UserEntity(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<UserEntity>(Users)
    var username by Users.username
    var password by Users.password
    var name by Users.name
    var surname by Users.surname
    var age by Users.age
    var created by Users.created

    fun toUser(): UserDTO = UserDTO(this.id.value, this.username, this.name, this.surname, this.password, this.age)

}
