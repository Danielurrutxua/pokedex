package com.example.pokemon.datasource.service

import com.example.pokemon.api.serviceLocator
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.notNullValue
import org.hamcrest.core.IsEqual
import org.junit.Rule
import org.junit.Test

class GetAllPokemonRequestSuccessContractTest {

    @get:Rule
    val wireMockRule = WireMockRule(WireMockConfiguration.wireMockConfig().dynamicPort())

    @Test
    fun successfulRequestForAllPokemon() {

        val service = serviceLocator(wireMockRule.baseUrl())

        val result = service.getPokemonList().execute()

        assertThat(result.code(), IsEqual(200))
        assertThat(result.body(), notNullValue())
        assertThat(result.body(), hasSize(1))

        val pokemon = result.body()!![0]

        assertThat(pokemon.name, notNullValue())
        assertThat(pokemon.id, notNullValue())
        assertThat(pokemon.types, hasSize(2))
        assertThat(pokemon.favourite, notNullValue())

    }
}