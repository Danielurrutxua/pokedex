package com.example.pokemon.data.source.pokemon

import com.example.pokemon.services.RetrofitBuilder
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.notNullValue
import org.hamcrest.core.IsEqual
import org.junit.Rule
import org.junit.Test

class GetPokemonByIdRequestSuccessContractTest {

    @get:Rule
    val wireMockRule = WireMockRule(WireMockConfiguration.wireMockConfig().dynamicPort())

    @Test
    fun successfulRequestForASinglePokemon() {

        val service = RetrofitBuilder.build(wireMockRule.baseUrl()).create(PokemonService::class.java)

        val result = service.getPokemonById("0").execute()

        assertThat(result.code(), IsEqual(200))
        assertThat(result.body(), notNullValue())

        val pokemon = result.body()!!

        assertThat(pokemon.name, notNullValue())
        assertThat(pokemon.id, notNullValue())
        assertThat(pokemon.types, hasSize(2))
        assertThat(pokemon.favourite, notNullValue())
        assertThat(pokemon.stats, notNullValue())
        assertThat(pokemon.abilities, hasSize(2))
        assertThat(pokemon.height, notNullValue())
        assertThat(pokemon.weight, notNullValue())

    }
}