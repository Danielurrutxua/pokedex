package com.example.pokemon.adapter

import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.api.*

class ApiResponseAdapter {

    companion object {

        fun map(
            pokemonResponse: PokemonResponse
        ): Pokemon {
            val id = Pokemon.adaptPokemonId(pokemonResponse.id)
            val name = Pokemon.firstLetterUpper(pokemonResponse.name)
            val imgUrl = createImgUrs(pokemonResponse.sprites)
            val height = (pokemonResponse.height.toDouble() / 10).toString().plus(" m")
            val weight = (pokemonResponse.weight.toDouble() / 10).toString().plus(" kg")
            val listAbilities = createAbilities(pokemonResponse.abilities)
            val listTypes = createTypes(pokemonResponse.types)
            val stats = createStats(pokemonResponse.stats)

            return Pokemon(
                id,
                name,
                imgUrl,
                height,
                weight,
                listAbilities,
                listTypes as MutableList<String>,
                stats
            )
        }

        private fun createAbilities(response: List<Ability>): List<String> {
            val listAbilities = mutableListOf<String>()
            for (i in response) {
                val name = i.ability.name
                listAbilities.add(name)
            }
            return listAbilities
        }

        private fun createTypes(response: List<Type>): List<String> {
            val listTypes = mutableListOf<String>()
            for (i in response) {
                val name = i.type.name
                listTypes.add(name)
            }
            return listTypes
        }

        private fun createImgUrs(response: Sprite): List<String> {

            val imgUrls = mutableListOf<String>()
            imgUrls.add(response.frontDefault)
            imgUrls.add(response.backDefault)
            imgUrls.add(response.frontShiny)
            imgUrls.add(response.backShiny)

            return imgUrls
        }

        private fun createStats(response: List<Stats>): List<String> {
            val stats = mutableListOf<String>()
            response.forEach {
                stats.add(it.baseStat.toString())
            }
            return stats
        }
    }

}