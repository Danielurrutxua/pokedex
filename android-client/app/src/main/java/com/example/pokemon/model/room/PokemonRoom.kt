package com.example.pokemon.model.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonRoom(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @NonNull @ColumnInfo(name = "name") val name: String,
    @NonNull @ColumnInfo(name = "height") val height: String,
    @NonNull @ColumnInfo(name = "weight") val weight: String,
    @NonNull @ColumnInfo(name= "favourite") val favourite: Boolean
)

@Entity(tableName = "img_urls")
data class ImgUrls(
    @PrimaryKey @ColumnInfo(name = "front_default") val frontDefault: String,
    @ColumnInfo(name = "back_default") val backDefault: String?,
    @ColumnInfo(name = "front_shiny") val frontShiny: String?,
    @ColumnInfo(name = "back_shiny") val backShiny: String?
)

@Entity(tableName = "stats")
data class StatsRoom(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "base_stat") val baseStat: String,
)

@Entity(tableName = "pokemon_ability", primaryKeys = ["pokemon_id", "ability_name"])
data class PokemonAndAbility(
    @ColumnInfo(name = "pokemon_id") val pokemonId: String,
    @ColumnInfo(name = "ability_name") val abilityName: String
)

@Entity(tableName = "pokemon_type", primaryKeys = ["pokemon_id", "type_name"])
data class PokemonAndType(
    @ColumnInfo(name = "pokemon_id") val pokemonId: String,
    @ColumnInfo(name = "type_name") val typeId: String
)

@Entity(tableName = "pokemon_img", primaryKeys = ["pokemon_id", "front_default"])
data class PokemonAndImg(
    @ColumnInfo(name = "pokemon_id") val pokemonId: String,
    @ColumnInfo(name = "front_default") val frontDefault: String
)

@Entity(tableName = "pokemon_stats", primaryKeys = ["pokemon_id", "stats_id"])
data class PokemonAndStats(
    @ColumnInfo(name = "pokemon_id") val pokemonId: String,
    @ColumnInfo(name = "stats_id") val typeId: String
)