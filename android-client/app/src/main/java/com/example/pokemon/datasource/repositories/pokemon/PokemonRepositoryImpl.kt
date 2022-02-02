package com.example.pokemon.datasource.repositories.pokemon

import com.example.pokemon.model.Pokemon
import com.example.pokemon.datasource.pokemon.PokemonDataSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class PokemonRepositoryImpl(
    private val pokemonDataSource: PokemonDataSource
): PokemonRepository {

    @InternalCoroutinesApi
    @FlowPreview
    override fun getPokemonDetail(id: String): Flow<Pokemon?> {
        return pokemonDataSource.getPokemon(id)
    }

    override fun getAllPokemon(): Flow<List<Pokemon>> {
        return pokemonDataSource.getPokemonList()
    }


}