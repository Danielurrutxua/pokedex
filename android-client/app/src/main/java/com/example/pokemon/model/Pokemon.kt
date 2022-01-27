package com.example.pokemon.model


data class Pokemon(
    val id: String,
    val name: String,
    val imgUrls: List<String>,
    val height: String,
    val weight: String,
    var abilities: List<String>,
    var types: MutableList<String>,
    val stats: List<String>,
    var fav: Boolean = false
) {


    companion object {

        fun adaptPokemonId(num: String): String {
            return when (num.length) {
                1 -> "00".plus(num)
                2 -> "0".plus(num)
                else -> num
            }
        }

        fun removeZeros(id: String): String {
            var result = id
            while (result.startsWith("0")) {
                result = result.drop(1)
            }
            return result
        }

        fun firstLetterUpper(name: String): String {
            return name[0].uppercase() + name.substring(1)
        }

    }
}

data class PokemonFavourite(
    val name: String,
    val favourite: Boolean
)