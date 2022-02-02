package com.example.pokemon.datasource.pokemon

import com.example.pokemon.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonDataSource {

    fun getPokemon(id: String): Flow<Pokemon>

    fun getPokemonList(): Flow<List<Pokemon>>

}