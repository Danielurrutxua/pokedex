package com.example.pokemon.pokeapi.datasource.database

import com.example.pokemon.adapter.PokemonRealmAdapter
import com.example.pokemon.adapter.PokemonToPokemonRealmAdapter
import com.example.pokemon.getRealmProMode
import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.PokemonFavourite
import com.example.pokemon.model.realm.PokemonRealm
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow


class PokemonRealmDBDatasource : PokemonDBDataSource {

    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    override fun getPokemon(name: String): Flow<Pokemon?> {

        return channelFlow {
            getRealmProMode {
                val pokemonRealm = it.where(PokemonRealm::class.java)
                    .equalTo("name", Pokemon.firstLetterUpper(name))
                    .findFirst()

            this.send(PokemonRealmAdapter.map(pokemonRealm))
            }
            awaitClose {
                this.cancel()
            }
        }

    }

    override fun getPokemonFavourites(): List<PokemonFavourite>? {
        TODO("Not yet implemented")
    }

    override suspend fun savePokemon(pokemon: Pokemon) {
        getRealmProMode {
            it.executeTransaction { it1 ->
                it1.copyToRealm(PokemonToPokemonRealmAdapter.map(pokemon))
            }
        }
    }

    override suspend fun updatePokemon(pokemon: Pokemon) {
        TODO("Not yet implemented")
    }
}


