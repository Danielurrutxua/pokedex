package com.example.pokemon.pokeapi.datasource.service

import android.util.Log
import com.example.pokemon.model.Pokemon
import com.example.pokemon.pokeapi.PokeApiService
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokeApiDataSource(private val pokeApiService: PokeApiService) : PokemonServiceDataSource {

    private val TAG = "API_CALL"

    @FlowPreview
    override fun getPokemon(name: String): Flow<Pokemon> {

        return flow {
            try {
                val response = pokeApiService.getPokemonById(name).execute()
                response.body()?.let {
                    this.emit(it)
                }
            } catch (t: Throwable) {
                Log.e(TAG, "error getting pokemon", t)
            }
        }
    }

    override fun getPokemonList(): Flow<List<Pokemon>> {

        return flow {
            try {
                val response = pokeApiService.getPokemonList().execute()
                response.body()?.let { results ->
                    this.emit(results)
                }
            } catch (t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        }
    }

}