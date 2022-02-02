package com.example.pokemon.datasource.pokemon

import com.example.pokemon.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon")
    fun getPokemonList(): Call<List<Pokemon>>

    @GET("pokemon/{id}")
    fun getPokemonById(@Path(value = "id") id: String): Call<Pokemon>


}

