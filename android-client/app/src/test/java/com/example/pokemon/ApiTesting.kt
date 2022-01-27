package com.example.pokemon

import android.provider.Telephony.Carriers.PORT
import com.example.pokemon.model.api.PokemonResponse
import com.example.pokemon.pokeapi.service
import retrofit2.Call
import com.github.tomakehurst.wiremock.client.WireMock.*

import com.github.tomakehurst.wiremock.junit.WireMockRule

import org.junit.Rule

import org.junit.Test

import org.junit.Assert.*

import com.github.tomakehurst.wiremock.client.WireMock.matching

import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo

import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor

import com.github.tomakehurst.wiremock.client.WireMock.containing

import com.github.tomakehurst.wiremock.client.WireMock.stubFor









class ApiTesting {
    val pokeApiService = service

    @Rule
    var wireMockRule = WireMockRule(8089) // No-args constructor defaults to port 8080

    @Test
    fun exampleTest() {
        stubFor(
            get("https://pokeapi.co/api/v2/pokemon/1")
                .withHeader("Content-Type", containing("json"))
                .willReturn(
                    ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>SUCCESS</response>")
                )
        )
        val serviceEndpoint = "http://127.0.0.1:" + BuildConfig.PORT
        val result: Call<PokemonResponse> = service.getPokemonByName("pikachu")
        assertTrue(result.isExecuted)
        verify(
            postRequestedFor(urlPathEqualTo("/my/resource"))
                .withRequestBody(matching(".*message-1234.*"))
                .withHeader("Content-Type", equalTo("text/xml"))
        )

        assertEquals(0,0)
    }

}