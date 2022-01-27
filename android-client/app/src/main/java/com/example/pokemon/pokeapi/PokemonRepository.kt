package com.example.pokemon.pokeapi

import android.util.Log
import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.PokemonFavourite
import com.example.pokemon.pokeapi.datasource.database.PokemonDBDataSource
import com.example.pokemon.pokeapi.datasource.service.PokemonServiceDataSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

class PokemonRepository(
    private val pokemonDBDataSource: PokemonDBDataSource,
    private val pokemonServiceDataSource: PokemonServiceDataSource
) {

    @InternalCoroutinesApi
    @FlowPreview
    suspend fun getPokemonDetail(name: String): Flow<Pokemon?> {

        return flow {
            pokemonDBDataSource.getPokemon(Pokemon.firstLetterUpper(name)).collect { pokemon ->
                if (pokemon != null) {
                    Log.d("ResultDB", pokemon.name)
                    this.emit(pokemon)
                } else {

                    pokemonServiceDataSource.getPokemon(name).collect { pokemon ->
                        Log.d("ResultApi", pokemon.name)

                        pokemonServiceDataSource.getPokemonTypes(pokemon.types).collect { types ->
                            pokemonServiceDataSource.getPokemonAbilities(pokemon.abilities)
                                .collect { abilities ->
                                    val pokemon1 = pokemon
                                    pokemon1.types = types as MutableList<String>
                                    pokemon1.abilities = abilities

                                    pokemonDBDataSource.savePokemon(pokemon)
                                    this.emit(pokemon)

                                }
                        }

                    }
                }
            }
        }
    }

    fun getAllPokemon(): Flow<List<Pokemon>> {
        val pokemonListFlow = pokemonServiceDataSource.getPokemonList()
        val pokemonTypesFlow = pokemonServiceDataSource.getPokemonListTypes()
        val pokemonFavourites = pokemonDBDataSource.getPokemonFavourites()

        return combine(pokemonListFlow, pokemonTypesFlow) { pokemonList, pokemonTypes ->
            val newPokemonList = addFavouriteValuesToPokemonList(pokemonList, pokemonFavourites)
            addTypesToPokemonList(newPokemonList, pokemonTypes)
        }
    }

    private fun addFavouriteValuesToPokemonList(
        pokemonList: List<Pokemon>,
        pokemonFavourites: List<PokemonFavourite>?
    ): List<Pokemon> {
        pokemonFavourites?.forEach { pokemonFavourite ->
            val pokemonNamesList = pokemonList.map { it.name }
            val index = pokemonNamesList.indexOf(pokemonFavourite.name)
            pokemonList[index].fav = pokemonFavourite.favourite
        }
        return pokemonList
    }



    private fun addTypesToPokemonList(
        pokemonList: List<Pokemon>,
        pokemonTypes: Map<String, List<String>>
    ): List<Pokemon> {

        pokemonTypes.forEach { pair ->
            val names = pair.value.map { Pokemon.firstLetterUpper(it) }
            names.forEach { name ->
                val index = pokemonList.map { it.name }.indexOf(name)
                pokemonList[index].types.add(pair.key)
            }
        }
        return pokemonList
    }

    suspend fun updatePokemon(pokemon: Pokemon) {
        pokemonDBDataSource.updatePokemon(pokemon)
    }

    fun getPokemonFavourites(): List<PokemonFavourite>? {
       return pokemonDBDataSource.getPokemonFavourites()
    }
}