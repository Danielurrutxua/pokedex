package com.example.pokemon.data.source.repository.pokemon

import com.example.pokemon.data.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonDetail(id: String): Flow<Pokemon?>

    fun getAllPokemon(): Flow<List<Pokemon>>
}