package com.example.pokemon.data.source.pokemon

import com.example.pokemon.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonDataSource {

    fun getPokemon(id: String): Flow<Pokemon>

    fun getPokemonList(): Flow<List<Pokemon>>

}