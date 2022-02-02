package com.example.pokemon.datasource.repositories.pokemon

import com.example.pokemon.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonDetail(id: String): Flow<Pokemon?>

    fun getAllPokemon(): Flow<List<Pokemon>>
}