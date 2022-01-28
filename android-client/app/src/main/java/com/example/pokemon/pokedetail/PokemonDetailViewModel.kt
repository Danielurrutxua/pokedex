package com.example.pokemon.pokedetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemon.model.Pokemon
import com.example.pokemon.pokeapi.PokemonRepository
import com.example.pokemon.pokeapi.getPokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokemonDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> = _pokemon
    lateinit var imgUrls: List<String>
    private var list = mutableListOf<String>()
    private val repository: PokemonRepository = getPokemonRepository(application)

    @InternalCoroutinesApi
    @FlowPreview
    fun loadPokemonDetail(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPokemonDetail(name).collect { pokemon ->
                _pokemon.postValue(pokemon)
                generateImgUrls(pokemon!!.id)
            }
        }
    }

    private fun generateImgUrls(id: Int) {
        imgUrls = listOf(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/$id.png",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$id.png",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/$id.png",
        )
    }

    @FlowPreview
    @InternalCoroutinesApi
    fun saveArguments(name: String, listNames: List<String>) {
        list = listNames as MutableList<String>
        loadPokemonDetail(name)
    }

    @FlowPreview
    @InternalCoroutinesApi
    fun nextName() {
        if (pokemon.value?.name != null) {
            if (list.indexOf(pokemon.value!!.name) < list.lastIndex) {
                val position = list.indexOf(pokemon.value!!.name) + 1
                loadPokemonDetail(list[position].lowercase())
            }
        }
    }


    @FlowPreview
    @InternalCoroutinesApi
    fun previousName() {
        if (pokemon.value?.name != null) {
            if (list.indexOf(pokemon.value!!.name) > 0) {
                val position = list.indexOf(pokemon.value!!.name) - 1
                loadPokemonDetail(list[position].lowercase())
            }
        }
    }

    fun changeFavValue(value: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val pokemon = pokemon.value
//            pokemon!!.fav = value
//            _pokemon.postValue(pokemon)
//            repository.updatePokemon(pokemon)
        }
    }

}
