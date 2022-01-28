package com.example.pokemon.pokeapi

import android.util.Log
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
//            pokemonDBDataSource.getPokemon(Pokemon.firstLetterUpper(name)).collect { pokemon ->
//                if (pokemon != null) {
//                    Log.d("ResultDB", pokemon.name)
//                    this.emit(Pokemon("2","2", listOf(), false, null,null,null,null))
//                } else {

            pokemonServiceDataSource.getPokemon(name).collect { pokemon ->
                Log.d("ResultApi", pokemon.name)

                this.emit(pokemon)

            }
        }
    }


    fun getAllPokemon(): Flow<List<Pokemon>> {
        return pokemonServiceDataSource.getPokemonList()

    }


}