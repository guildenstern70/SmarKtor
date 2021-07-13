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
import io.ktor.http.*
import io.ktor.server.testing.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import kotlin.test.assertEquals

@DisplayName("Smarktor App")
class ApplicationTest
{
    @Test
    fun `should get home page`()
    {
        withTestApplication(Application::module)
        {
            handleRequest(HttpMethod.Get, "/index").apply {
                assertThat(response.status()).isEqualTo(HttpStatusCode.OK)
            }
        }
    }

    @Test
    fun `should get all users`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/api/v1/users").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                val content = response.content
                assertThat(content).startsWith("[{\"id\":1,\"username\":\"alessio\"")
            }
        }
    }

    @Test
    fun `should get specific user`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/api/v1/users/1").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                val content = response.content
                assertThat(content).startsWith("{\"id\":1,\"username\":\"alessio\"")
            }
        }
    }

    @Test
    fun `should get specific user by username`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/api/v1/users/byusername/alessio").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                val content = response.content
                assertThat(content).startsWith("{\"id\":1,\"username\":\"alessio\"")
            }
        }
    }

    @Test
    fun `should create a new user`() {
        withTestApplication(Application::module) {
            with(handleRequest(HttpMethod.Post, "/api/v1/users"){
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody("""
                    {
                      "username": "Ely75",
                      "name": "Elena",
                      "surname": "Zambrelli",
                      "password": "prova",
                      "age": "16"
                    }
                """.trimIndent()
                )
            }) {
                assertThat(response.status()).isEqualTo(HttpStatusCode.Created)
            }
        }
    }
}
