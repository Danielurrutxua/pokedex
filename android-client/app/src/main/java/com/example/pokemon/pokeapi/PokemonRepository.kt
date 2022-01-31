package com.example.pokemon.pokeapi

import com.example.pokemon.model.Pokemon
import com.example.pokemon.pokeapi.datasource.service.PokemonServiceDataSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class PokemonRepository(
    private val pokemonServiceDataSource: PokemonServiceDataSource
) {

    @InternalCoroutinesApi
    @FlowPreview
    suspend fun getPokemonDetail(name: String): Flow<Pokemon?> {

        return flow {
            pokemonServiceDataSource.getPokemon(name).collect { pokemon ->
                this.emit(pokemon)
            }
        }
    }

    fun getAllPokemon(): Flow<List<Pokemon>> {
        return pokemonServiceDataSource.getPokemonList()

    }


}