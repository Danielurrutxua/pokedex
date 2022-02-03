package com.example.pokemon.data.source.user

import com.example.pokemon.data.model.User
import com.example.pokemon.services.RetrofitBuilder
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.Rule
import org.junit.Test

class RegisterRequestSuccessContractTest {

    @get:Rule
    val wireMockRule = WireMockRule(WireMockConfiguration.wireMockConfig().dynamicPort())

    @Test
    fun successfulRegisterRequest() {

        val service =
            RetrofitBuilder.build(wireMockRule.baseUrl()).create(UserService::class.java)

        val response = service.registerUser(User(
            id = 1, username = "drubidu226",
            email = "drub@gmail.com", password = "password")).execute()

        MatcherAssert.assertThat(response.code(), IsEqual(200))
    }
}
