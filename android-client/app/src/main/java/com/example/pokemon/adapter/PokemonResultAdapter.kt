package com.example.pokemon.adapter

import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.api.PokemonResult

class PokemonResultAdapter {

    companion object {
        fun map(response: List<PokemonResult>): List<Pokemon> {
            val pokemonList: MutableList<Pokemon> = mutableListOf()

            for (item in response.indices) {

                val id = response[item].url.split("/")[6]
                val name = Pokemon.firstLetterUpper(response[item].name)
                val imgUrls = mutableListOf("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png")
                val pokemon = Pokemon(
                    Pokemon.adaptPokemonId(id), name, imgUrls, "0", "0", listOf(), mutableListOf(), listOf()
                )
                pokemonList.add(pokemon)
            }
            return pokemonList
        }
    }
}