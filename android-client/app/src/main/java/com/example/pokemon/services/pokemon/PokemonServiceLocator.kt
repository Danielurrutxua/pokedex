package com.example.pokemon.services.pokemon

import androidx.annotation.VisibleForTesting
import com.example.pokemon.services.RetrofitBuilder
import com.example.pokemon.data.source.repository.pokemon.PokemonRepository
import com.example.pokemon.data.source.repository.pokemon.PokemonRepositoryImpl
import com.example.pokemon.data.source.pokemon.PokemonDataSource
import com.example.pokemon.data.source.pokemon.PokemonDataSourceImpl
import com.example.pokemon.data.source.pokemon.PokemonService

object PokemonServiceLocator {

    @Volatile
    var pokemonRepository: PokemonRepositoryImpl? = null
        @VisibleForTesting set

    fun providePokemonRepository(base_url: String): PokemonRepository {
        synchronized(this) {
            return pokemonRepository ?: createPokemonRepository(base_url)
        }
    }

    private fun createPokemonRepository(base_url: String): PokemonRepository {
        val newRepo = PokemonRepositoryImpl(createPokemonDataSource(base_url))
        pokemonRepository = newRepo

        return newRepo
    }

    private fun createPokemonDataSource(base_url: String): PokemonDataSource {
        val pokemonService = RetrofitBuilder.build(base_url).create(PokemonService::class.java)
        return PokemonDataSourceImpl(pokemonService)
    }
}