package com.example.pokemon.pokelist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemon.model.Pokemon
import com.example.pokemon.pokeapi.PokemonRepository
import com.example.pokemon.pokeapi.getPokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*


class ListviewViewModel(application: Application) : AndroidViewModel(application) {

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList
    private lateinit var originalPokemonList: MutableList<Pokemon>
    private val repository: PokemonRepository = getPokemonRepository(application)
    var menuItemSelected = MutableLiveData<Int>()
    var loadFavourites = false

    private val filters = Filters()

    init {
        getAllPokemon()
    }

    private fun getAllPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            val pokemonListFlow = repository.getAllPokemon()
            pokemonListFlow.collect { pokemonList ->
                _pokemonList.postValue(pokemonList)
                addFavouriteValuesToPokemonList()
                originalPokemonList = _pokemonList.value as MutableList<Pokemon>
                filterList()
            }
        }
    }

    private fun filterList() {

        var newList = originalPokemonList

        newList = filterSearchText(newList) as MutableList<Pokemon>
        if (filters.isFav) newList = newList.filter { it.fav } as MutableList<Pokemon>
        if (filters.isFire) newList =
            newList.filter { it.types.contains("Fuego") } as MutableList<Pokemon>
        if (filters.isPlant) newList =
            newList.filter { it.types.contains("Planta") } as MutableList<Pokemon>
        if (filters.isFlying) newList =
            newList.filter { it.types.contains("Volador") } as MutableList<Pokemon>
        if (filters.isPoison) newList =
            newList.filter { it.types.contains("Veneno") } as MutableList<Pokemon>

        _pokemonList.postValue(reorderList(filters.listOrder, newList))

    }

    private fun filterSearchText(newList: List<Pokemon>): List<Pokemon> {
        if (filters.searchText?.isNotBlank() == true) {
            lateinit var newList1: List<Pokemon>
            if (filters.searchText!![0].isLetter()) {
                newList1 = newList.filter {
                    it.name.lowercase(Locale.getDefault()).contains(
                        filters.searchText!!.lowercase(Locale.getDefault())
                    )
                } as MutableList<Pokemon>

            } else if (filters.searchText!![0].isDigit()) {
                newList1 = if (filters.searchText!![0] == '0') {
                    newList.filter {
                        it.id.startsWith(
                            filters.searchText!!
                        )
                    } as MutableList<Pokemon>
                } else {
                    newList.filter {
                        Pokemon.removeZeros(it.id).startsWith(
                            filters.searchText!!
                        )
                    } as MutableList<Pokemon>
                }

            }
            return newList1
        } else return newList

    }

    fun changeFavFilter(isFav: Boolean) {
        filters.isFav = isFav
        filterList()
    }

    fun changeTypesFilter(types: List<Boolean>) {
        filters.isFire = types[0]
        filters.isFlying = types[1]
        filters.isPlant = types[2]
        filters.isPoison = types[3]
        filterList()
    }

    fun changeListOrder(listOrder: Int) {
        filters.listOrder = listOrder
        filterList()
    }

    fun changeSearchText(newText: String?) {
        filters.searchText = newText
        filterList()
    }

    private fun reorderList(num: Int, list: List<Pokemon>): List<Pokemon> {
        return when (num) {
            0 -> list.sortedBy { Pokemon.removeZeros(it.id).toInt() }
            1 -> list.sortedBy { Pokemon.removeZeros(it.id).toInt() }.reversed()
            2 -> list.sortedBy { it.name }
            else -> list.sortedBy { it.name }.reversed()
        }
    }

    private fun addFavouriteValuesToPokemonList() {
        val pokemonFavourites = repository.getPokemonFavourites()
        val pokemonList1 = pokemonList.value!!
        pokemonFavourites?.forEach { pokemonFavourite ->
            val pokemonNamesList = pokemonList1.map { it.name }
            val index = pokemonNamesList.indexOf(pokemonFavourite.name)
            if (index != -1) pokemonList1[index].fav = pokemonFavourite.favourite
        }
        _pokemonList.postValue(pokemonList1)
    }

    fun needToAddFavValues() {
        if (loadFavourites) {
            viewModelScope.launch(Dispatchers.IO) {
                addFavouriteValuesToPokemonList()
                loadFavourites = false
            }
        }
    }
}