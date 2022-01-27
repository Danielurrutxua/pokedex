package com.example.pokemon.pokeapi.datasource.database

import android.util.Log
import com.example.pokemon.adapter.PokemonToPokemonRoomAdapter
import com.example.pokemon.database.PokemonDao
import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.PokemonFavourite
import com.example.pokemon.model.room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class PokemonRoomDBDatasource(private val pokemonDao: PokemonDao) : PokemonDBDataSource {

    override fun getPokemon(name: String): Flow<Pokemon?> {
        return flow {
            val pokemon = pokemonDao.getPokemonByName(name)
            if (pokemon != null) {
                val abilities = getAbilitiesFromDb(pokemon.id)
                val types = getTypesFromDb(pokemon.id)
                val imgUrls = getImgUrlsFromDb(pokemon.id)
                val stats = getStatsFromDb(pokemon.id)

                this.emit(
                    Pokemon(
                        pokemon.id,
                        Pokemon.firstLetterUpper(pokemon.name),
                        imgUrls,
                        pokemon.height,
                        pokemon.weight,
                        abilities,
                        types as MutableList<String>,
                        stats,
                        pokemon.favourite
                    )
                )
            } else this.emit(null)
        }
    }

    override fun getPokemonFavourites(): List<PokemonFavourite>? {
        return pokemonDao.getPokemonFavourites()

    }


    override suspend fun savePokemon(pokemon: Pokemon) {

        GlobalScope.launch(Dispatchers.IO) {
            val pokemonRoom = PokemonToPokemonRoomAdapter.map(pokemon)
            pokemonDao.insertPokemon(pokemonRoom)
            //GUARDAR HABILIDADES
            pokemon.abilities.forEach { ability ->
                val pokemonAndAbility = PokemonAndAbility(pokemon.id, ability)
                pokemonDao.insertPokemonAbility(pokemonAndAbility)
            }
            //GUARDAR TIPOS
            pokemon.types.forEach { type ->
                val pokemonAndType = PokemonAndType(pokemon.id, type)
                pokemonDao.insertPokemonType(pokemonAndType)
            }
            Log.d("TAG", pokemon.id)
            //GUARDAR ESTADISTICAS
            pokemon.stats.forEach { stat ->
                val statId = getRandomId()
                pokemonDao.insertStats(StatsRoom(statId, stat))
                pokemonDao.insertPokemonStats(PokemonAndStats(pokemon.id, statId))
            }
            Log.d("TAG", pokemon.id)
            //GUARDAR IMAGENES
            pokemonDao.insertImgUrls(
                ImgUrls(
                    pokemon.imgUrls[0],
                    pokemon.imgUrls[1],
                    pokemon.imgUrls[2],
                    pokemon.imgUrls[3]
                )
            )
            Log.d("TAG", pokemon.id)
            pokemonDao.insertPokemonImgUrls(PokemonAndImg(pokemon.id, pokemon.imgUrls[0]))
        }
    }

    override suspend fun updatePokemon(pokemon: Pokemon) {
        GlobalScope.launch(Dispatchers.IO) {
            val pokemonRoom = PokemonToPokemonRoomAdapter.map(pokemon)
            pokemonDao.updatePokemon(pokemonRoom)
        }

    }


    private fun getAbilitiesFromDb(pokemonId: String): List<String> {
        val abilities = mutableListOf<String>()
        val abilitiesId = pokemonDao.getAbilitiesIdFromPokemon(pokemonId)
        abilitiesId.forEach { abilityId ->
            abilities.add(abilityId)
        }
        return abilities
    }

    private fun getTypesFromDb(pokemonId: String): List<String> {
        val types = mutableListOf<String>()
        val typesId = pokemonDao.getTypesIdFromPokemon(pokemonId)
        typesId.forEach { typeId ->
            types.add(typeId)
        }
        return types
    }

    private fun getImgUrlsFromDb(pokemonId: String): List<String> {
        val imgUrls = mutableListOf<String>()

        val id = pokemonDao.getImgIdFromPokemon(pokemonId)
        pokemonDao.getImgUrlsById(id)?.let {
            imgUrls.add(it.frontDefault)
            it.backDefault?.let { it1 -> imgUrls.add(it1) }
            it.frontShiny?.let { it1 -> imgUrls.add(it1) }
            it.backShiny?.let { it1 -> imgUrls.add(it1) }
        }


        return imgUrls
    }

    private fun getStatsFromDb(pokemonId: String): List<String> {
        val stats = mutableListOf<String>()
        val statsId = pokemonDao.getStatsIdFromPokemon(pokemonId)
        statsId.forEach { id -> pokemonDao.getStatsById(id).let { stats.add(it!!.baseStat) } }
        return stats
    }

    private fun getRandomId(): String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..8)
            .map { charset.random() }
            .joinToString("")
    }

}