package com.example.pokemon.pokeapi.datasource.service

import com.example.pokemon.pokeapi.PokeApiService
import com.example.pokemon.pokeapi.serviceLocator
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.hamcrest.core.IsEqual
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetAllPokemonRequestSuccessContractTest {

    @get:Rule
    val wireMockRule = WireMockRule(WireMockConfiguration.wireMockConfig().dynamicPort())

    @Test
    fun successfulRequestForAllPokemon() {

        stubFor(
            get("/pokemon")
                .willReturn(
                    ok()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"types\":[\"water\",\"fire\"],\"name\":\"pikachu\",\"id\":0,\"favourite\":true}]")
                )
        );

        val service = serviceLocator(wireMockRule.baseUrl())

        val result = service.getPokemonList().execute()

        assertThat(result.body(), hasSize(1))
        assertThat(result.isSuccessful, IsEqual(true))


    }
}