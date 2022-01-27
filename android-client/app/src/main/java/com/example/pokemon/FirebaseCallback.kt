package com.example.pokemon

import com.example.pokemon.model.Pokemon

interface FirebaseCallback {
    fun onResponse(pokemonList: List<Pokemon>)
}