package com.example.pokemon.pokeapi.datasource.service

import android.util.Log
import com.example.pokemon.adapter.ApiResponseAdapter
import com.example.pokemon.adapter.PokemonResultAdapter
import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.api.Names
import com.example.pokemon.pokeapi.PokeApiService
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class PokeApiDataSource(private val pokeApiService: PokeApiService) : PokemonServiceDataSource {

    private val TAG = "PokemonApiDataSourceImpl"

    @FlowPreview
    override fun getPokemon(name: String): Flow<Pokemon> {
        return flow {
            try {
                val response = pokeApiService.getPokemonByName(name).execute()
                response.body()?.let {
                    this.emit(it)
                }
            } catch (t: Throwable) {
                Log.e(TAG, "error getting pokemon", t)
            }
        }.map {

            ApiResponseAdapter.map(it)
        }
    }

    override fun getPokemonTypes(names: List<String>): Flow<List<String>> {
        return flow {
            try {
                val list = mutableListOf<String>()
                names.forEach { name ->
                    val response = pokeApiService.getTypeByName(name).execute()
                    response.body()?.let {
                        list.add(getSpanishName(it.names))
                    }
                }
                this.emit(list)
            } catch (t: Throwable) {
                Log.e(TAG, "error getting types")
            }
        }
    }

    override fun getPokemonAbilities(names: List<String>): Flow<List<String>> {
        return flow {
            try {
                val list = mutableListOf<String>()
                names.forEach { name ->
                    val response = pokeApiService.getAbilityByName(name).execute()
                    response.body()?.let {
                        list.add(getSpanishName(it.names))
                    }
                }
                this.emit(list)
            } catch (t: Throwable) {
                Log.e(TAG, "error getting abilities")
            }
        }
    }

    override fun getPokemonList(): Flow<List<Pokemon>> {

        return flow {
            try {
                val response = pokeApiService.getPokemonList().execute()
                response.body()?.results.let { results ->

                    if (results != null) {
                        val pokemonList = PokemonResultAdapter.map(results)
                        this.emit(pokemonList)
                    }
                }
            } catch (t: Throwable) {
                Log.e(TAG, "error getting pokemon")
            }
        }
    }

    override fun getPokemonListTypes(): Flow<Map<String, List<String>>> {
        return flow {
            try {
                val map = mutableMapOf<String, List<String>>()
                for (id in 1..18) {
                    val response = pokeApiService.getTypeByName(id.toString()).execute()
                    response.body()?.let { type ->
                        val list = type.pokemon.map {
                            it.pokemon.name
                        }
                        map.put(getSpanishName(type.names), list)
                    }
                }
                this.emit(map)
            } catch (t: Throwable) {
                Log.e(TAG, "error getting types")
            }
        }
    }

    private fun getSpanishName(names: List<Names>): String {
        var spanishName = ""
        names.forEach { name ->
            if (name.language.name == "es") spanishName = name.name
        }
        return spanishName
    }

}