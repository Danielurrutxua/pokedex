package com.example.pokemon.adapter

import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.room.PokemonRoom

class PokemonToPokemonRoomAdapter {

    companion object {

        fun map(pokemon: Pokemon): PokemonRoom {
            return PokemonRoom(
                Pokemon.adaptPokemonId(pokemon.id),
                pokemon.name,
                pokemon.height,
                pokemon.weight,
                pokemon.fav
            )
        }
    }
}