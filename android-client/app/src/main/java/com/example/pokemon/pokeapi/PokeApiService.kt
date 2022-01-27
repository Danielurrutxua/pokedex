package com.example.pokemon.pokeapi

import com.example.pokemon.model.api.PokeApiResponse
import com.example.pokemon.model.api.PokemonResponse
import com.example.pokemon.model.api.TypeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {

    @GET("pokemon?limit=1118&offset=0")
    fun getPokemonList(): Call<PokeApiResponse>

    @GET("pokemon/{name}")
    fun getPokemonByName(@Path(value = "name") name: String): Call<PokemonResponse>

    @GET("type/{name}")
    fun getTypeByName(@Path(value = "name") name: String): Call<TypeResponse>

    @GET("ability/{name}")
    fun getAbilityByName(@Path(value = "name") name: String): Call<TypeResponse>

}

