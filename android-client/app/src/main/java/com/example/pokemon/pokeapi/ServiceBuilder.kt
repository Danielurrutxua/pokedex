package com.example.pokemon.pokeapi

import com.example.pokemon.service.PokeApiDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://10.0.2.2:5000/"

fun serviceLocator(base_url: String): PokeApiService {
     val retrofit = Retrofit.Builder()
        .baseUrl(base_url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(PokeApiService::class.java)
}

fun getPokemonRepository(): PokemonRepository {
    return PokemonRepository(
        PokeApiDataSource(serviceLocator(BASE_URL))
    )
}


