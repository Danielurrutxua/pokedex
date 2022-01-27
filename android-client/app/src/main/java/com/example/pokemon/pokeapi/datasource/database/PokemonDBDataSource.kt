package com.example.pokemon.pokeapi.datasource.database

import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.PokemonFavourite
import kotlinx.coroutines.flow.Flow

interface PokemonDBDataSource {

    fun getPokemon(name: String): Flow<Pokemon?>

    fun getPokemonFavourites(): List<PokemonFavourite>?

    suspend fun savePokemon(pokemon: Pokemon)

    suspend fun updatePokemon(pokemon: Pokemon)


}