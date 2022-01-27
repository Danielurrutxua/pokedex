package com.example.pokemon

import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule
import com.github.tomakehurst.wiremock.client.WireMock.matching

import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo

import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor

import com.github.tomakehurst.wiremock.client.WireMock.containing

import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import junit.framework.Assert.assertTrue
import org.junit.Test


class WireMock {

    @Rule
    var wireMockRule = WireMockRule(8089) // No-args constructor defaults to port 8080

    @Test
    fun exampleTest() {
        stubFor(
            post("/my/resource")
                .withHeader("Content-Type", containing("xml"))
                .willReturn(
                    ok()
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>SUCCESS</response>")
                )
        )
        val result: Result = myHttpServiceCallingObject.doSomething()
        assertTrue(result.wasSuccessful())
        verify(
            postRequestedFor(urlPathEqualTo("/my/resource"))
                .withRequestBody(matching(".*message-1234.*"))
                .withHeader("Content-Type", equalTo("text/xml"))
        )
    }

}