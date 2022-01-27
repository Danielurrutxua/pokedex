package com.example.pokemon.database

import androidx.room.*
import com.example.pokemon.model.PokemonFavourite
import com.example.pokemon.model.room.*

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon WHERE name = :name")
    fun getPokemonByName(name: String): PokemonRoom?

    @Query("SELECT front_default FROM pokemon_img WHERE pokemon_id = :pokemonId")
    fun getImgIdFromPokemon(pokemonId: String): String

    @Query("SELECT stats_id FROM pokemon_stats WHERE pokemon_id = :pokemonId")
    fun getStatsIdFromPokemon(pokemonId: String): List<String>

    @Query("SELECT ability_name FROM pokemon_ability WHERE pokemon_id = :pokemonId")
    fun getAbilitiesIdFromPokemon(pokemonId: String): List<String>

    @Query("SELECT type_name FROM pokemon_type WHERE pokemon_id = :pokemonId")
    fun getTypesIdFromPokemon(pokemonId: String): List<String>

    @Query("SELECT * FROM stats WHERE id = :id")
    fun getStatsById(id: String): StatsRoom?

    @Query("SELECT * FROM img_urls WHERE front_default = :id")
    fun getImgUrlsById(id: String): ImgUrls?

    @Query("SELECT name, favourite FROM pokemon")
    fun getPokemonFavourites(): List<PokemonFavourite>?

    @Update
    suspend fun updatePokemon(vararg pokemon: PokemonRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(vararg pokemon: PokemonRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImgUrls(vararg imgUrls: ImgUrls)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(vararg statsRoom: StatsRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonAbility(vararg pokemonAndAbility: PokemonAndAbility)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonType(vararg pokemonAndType: PokemonAndType)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonImgUrls(vararg pokemonAndImg: PokemonAndImg)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonStats(vararg pokemonAndStats: PokemonAndStats)


}