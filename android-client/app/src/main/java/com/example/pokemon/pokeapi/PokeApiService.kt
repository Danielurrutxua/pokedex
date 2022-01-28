package com.example.pokemon.pokeapi

import com.example.pokemon.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {

    @GET("pokemon")
    fun getPokemonList(): Call<List<Pokemon>>

    @GET("pokemon/{id}")
    fun getPokemonById(@Path(value = "id") id: String): Call<Pokemon>


}

