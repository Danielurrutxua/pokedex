package com.example.pokemon.data.source.user

import com.example.pokemon.data.source.pokemon.PokemonService
import com.example.pokemon.services.RetrofitBuilder
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.notNullValue
import org.hamcrest.core.IsEqual
import org.junit.Rule
import org.junit.Test

class LoginUserSuccessContractTest {

    @get:Rule
    val wireMockRule = WireMockRule(WireMockConfiguration.wireMockConfig().dynamicPort())

    @Test
    fun successfulLoginRequest() {

        val service =
            RetrofitBuilder.build(wireMockRule.baseUrl()).create(UserService::class.java)

        val response = service.loginUser(email = "drub@gmail.com", password = "password").execute()

        assertThat(response.code(), IsEqual(200))
        assertThat(response.body()!!.email, IsEqual("drub@gmail.com"))
        assertThat(response.body()!!.username, IsEqual("drubidu226"))


    }
}