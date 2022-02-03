package com.example.pokemon.pokedetail

import android.app.Application
import androidx.lifecycle.*
import com.example.pokemon.MyApplication
import com.example.pokemon.data.model.Pokemon
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
    private val pokemonRepository = (application as MyApplication).pokemonRepository

    @InternalCoroutinesApi
    @FlowPreview
    fun loadPokemonDetail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.getPokemonDetail(id).collect { pokemon ->
                _pokemon.postValue(pokemon)
                generateImgUrls(pokemon!!.id)
            }
        }
    }

    fun adaptHeight(height:Int): String {
        return height.toDouble().div(10).toString().plus('m')
    }

    fun adaptWeight(weight:Int): String {
        return weight.toDouble().div(10).toString().plus("kg")
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
