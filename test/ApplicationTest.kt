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

import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest
{
    @Test
    fun testRoot()
    {
        withTestApplication({ module(testing = true) })
        {
            handleRequest(HttpMethod.Get, "/index").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}
