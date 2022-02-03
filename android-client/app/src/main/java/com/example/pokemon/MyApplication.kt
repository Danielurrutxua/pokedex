package com.example.pokemon

import android.app.Application
import com.example.pokemon.data.source.repository.pokemon.PokemonRepository
import com.example.pokemon.data.source.repository.user.UserRepository
import com.example.pokemon.services.pokemon.PokemonServiceLocator
import com.example.pokemon.services.user.UserServiceLocator

class MyApplication: Application() {

    private val baseUrl = "http://10.0.2.2:5000"

    val pokemonRepository: PokemonRepository
        get() = PokemonServiceLocator.providePokemonRepository(baseUrl)

    val userRepository: UserRepository
        get() = UserServiceLocator.provideUserRepository(baseUrl)

}