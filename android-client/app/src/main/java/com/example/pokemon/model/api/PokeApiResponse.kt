package com.example.pokemon.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class PokeApiResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)

data class TypeResponse(
    @Expose @SerializedName("names") val names: List<Names>,
    @Expose @SerializedName("pokemon") val pokemon: List<PokemonTypes>
)

data class AbilityResponse(
    @Expose @SerializedName("names") val names: List<Names>
)

data class Names(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("language") val language: Language
)

data class Language(
    @Expose @SerializedName("name") val name: String
)

data class PokemonTypes(
    @Expose @SerializedName("pokemon") val pokemon: PokemonTypesContent
)

data class PokemonTypesContent(
    @Expose @SerializedName("name") val name: String,
)

data class PokemonResponse(
    @Expose @SerializedName("id") val id: String,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("sprites") val sprites: Sprite,
    @Expose @SerializedName("height") val height: Int,
    @Expose @SerializedName("weight") val weight: Int,
    @Expose @SerializedName("abilities") val abilities: List<Ability>,
    @Expose @SerializedName("types") val types: List<Type>,
    @Expose @SerializedName("stats") val stats: List<Stats>
)

data class Sprite(
    @Expose @SerializedName("front_default") var frontDefault: String,
    @Expose @SerializedName("back_default") var backDefault: String,
    @Expose @SerializedName("front_shiny") var frontShiny: String,
    @Expose @SerializedName("back_shiny") var backShiny: String
)

data class Stats(
    @Expose @SerializedName("base_stat") val baseStat: Int
)

data class Ability(
    @Expose @SerializedName("ability") val ability: AbilityContent,
)

data class AbilityContent(
    @Expose @SerializedName("name") val name: String
)

data class Type(
    @Expose @SerializedName("type") val type: TypeContent
)

data class TypeContent(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)





