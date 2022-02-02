package com.example.pokemon.datasource.pokemon

import android.util.Log
import com.example.pokemon.model.Pokemon
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonDataSourceImpl(private val pokemonService: PokemonService) : PokemonDataSource {

    private val TAG = "API_CALL"

    @FlowPreview
    override fun getPokemon(id: String): Flow<Pokemon> {

        return flow {
            try {
                val response = pokemonService.getPokemonById(id).execute()
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
                val response = pokemonService.getPokemonList().execute()
                response.body()?.let { results ->
                    this.emit(results)
                }
            } catch (t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        }
    }

}