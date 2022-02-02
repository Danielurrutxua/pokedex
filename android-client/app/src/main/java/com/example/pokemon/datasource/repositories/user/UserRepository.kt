package com.example.pokemon.datasource.repositories.user

import com.example.pokemon.model.User
import com.example.pokemon.model.auth.LoginResponse
import com.example.pokemon.model.auth.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun loginUser(email: String, password: String): Flow<LoginResponse>

    fun registerUser(user: User): Flow<RegisterResponse>
}