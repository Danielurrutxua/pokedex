package com.example.pokemon.data.source.repository.pokemon

import com.example.pokemon.data.model.Pokemon
import com.example.pokemon.data.source.pokemon.PokemonDataSource
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