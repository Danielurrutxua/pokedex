package com.example.pokemon.datasource.user

import com.example.pokemon.model.User
import com.example.pokemon.model.auth.LoginResponse
import com.example.pokemon.model.auth.RegisterResponse
import kotlinx.coroutines.flow.Flow


interface UserDataSource {

    fun loginUser(email: String, password: String): Flow<LoginResponse>

    fun registerUser(user: User): Flow<RegisterResponse>

}