package com.example.pokemon.adapter

import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.realm.PokemonRealm

class PokemonRealmAdapter {

    companion object {
        fun map(pokemonRealm: PokemonRealm?): Pokemon? {
            return if (pokemonRealm != null) {

                Pokemon(
                    pokemonRealm.id,
                    pokemonRealm.name,
                    pokemonRealm.imgUrls,
                    pokemonRealm.height,
                    pokemonRealm.weight,
                    pokemonRealm.abilities,
                    pokemonRealm.types,
                    pokemonRealm.stats,
                    pokemonRealm.favourite
                )
            } else null
        }
    }
}