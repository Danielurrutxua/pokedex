package com.example.pokemon.adapter

import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.realm.PokemonRealm
import io.realm.RealmList

class PokemonToPokemonRealmAdapter {

    companion object {
        fun map(pokemon: Pokemon?): PokemonRealm? {
            return if (pokemon != null) {

                PokemonRealm(
                    pokemon.id,
                    pokemon.name,
                    pokemon.imgUrls as RealmList<String>,
                    pokemon.height,
                    pokemon.weight,
                    pokemon.abilities as RealmList<String>,
                    pokemon.types as RealmList<String>,
                    pokemon.stats as RealmList<String>,
                    pokemon.fav
                )
            } else null
        }
    }
}