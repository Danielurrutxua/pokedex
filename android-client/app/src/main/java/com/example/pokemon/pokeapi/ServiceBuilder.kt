package com.example.pokemon.pokeapi

import android.app.Application
import com.example.pokemon.database.PokemonDao
import com.example.pokemon.database.PokemonDatabase
import com.example.pokemon.pokeapi.datasource.database.PokemonRoomDBDatasource
import com.example.pokemon.pokeapi.datasource.service.PokeApiDataSource
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL =
    "https://pokeapi.co/api/v2/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

 val service: PokeApiService = retrofit.create(PokeApiService::class.java)

fun getPokemonDao(application: Application): PokemonDao {
    return PokemonDatabase.getDatabase(application).pokemonDao()
}

fun getFirestoreDatabase() : FirebaseFirestore {
    return FirebaseFirestore.getInstance()
}

fun getPokemonRepository(application: Application): PokemonRepository {
    return PokemonRepository(
        PokemonRoomDBDatasource(getPokemonDao(application)),
        //PokemonRealmDBDatasource(),
        //FirebaseServiceDataSource(getFirestoreDatabase())
        PokeApiDataSource(service)
    )
}


