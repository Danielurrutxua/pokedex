package com.example.pokemon.pokeapi

import com.example.pokemon.model.Pokemon
import com.example.pokemon.service.PokemonServiceDataSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class PokemonRepository(
    private val pokemonServiceDataSource: PokemonServiceDataSource
) {

    @InternalCoroutinesApi
    @FlowPreview
    fun getPokemonDetail(id: String): Flow<Pokemon?> {
        return pokemonServiceDataSource.getPokemon(id)
    }

    fun getAllPokemon(): Flow<List<Pokemon>> {
        return pokemonServiceDataSource.getPokemonList()
    }


}