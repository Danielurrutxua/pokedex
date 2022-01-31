package com.example.pokemon.pokeapi

import android.app.Application
import com.example.pokemon.pokeapi.datasource.service.PokeApiDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://10.0.2.2:5000/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

 val service: PokeApiService = retrofit.create(PokeApiService::class.java)


fun getPokemonRepository(application: Application): PokemonRepository {
    return PokemonRepository(

        PokeApiDataSource(service)
    )
}


