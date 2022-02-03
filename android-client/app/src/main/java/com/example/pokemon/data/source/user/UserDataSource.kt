package com.example.pokemon.data.source.user

import com.example.pokemon.data.model.User
import com.example.pokemon.data.model.auth.LoginResponse
import com.example.pokemon.data.model.auth.RegisterResponse
import kotlinx.coroutines.flow.Flow


interface UserDataSource {

    fun loginUser(email: String, password: String): Flow<LoginResponse>

    fun registerUser(user: User): Flow<RegisterResponse>

}