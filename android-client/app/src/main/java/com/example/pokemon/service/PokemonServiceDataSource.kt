package com.example.pokemon.service

import com.example.pokemon.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonServiceDataSource {

    fun getPokemon(id: String): Flow<Pokemon>

    fun getPokemonList(): Flow<List<Pokemon>>

}