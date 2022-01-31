package com.example.pokemon.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Pokemon(
    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("types") val types: List<String>,
    @Expose @SerializedName("favourite") var favourite: Boolean,
    @Expose @SerializedName("height") val height: Int?,
    @Expose @SerializedName("weight") val weight: Int?,
    @Expose @SerializedName("abilities") val abilities: List<String>?,
    @Expose @SerializedName("stats") val stats: Stats
){
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

data class Stats(
    @Expose @SerializedName("hp") val hp: Int,
    @Expose @SerializedName("attack") val attack: Int,
    @Expose @SerializedName("defense") val defense: Int,
    @Expose @SerializedName("special-attack") val special_attack: Int,
    @Expose @SerializedName("special-defense") val special_defense: Int,
    @Expose @SerializedName("speed") val speed: Int

)


data class PokemonFavourite(
    val name: String,
    val favourite: Boolean
)