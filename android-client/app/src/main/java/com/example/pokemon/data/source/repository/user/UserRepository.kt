package com.example.pokemon.data.source.repository.user

import com.example.pokemon.data.model.User
import com.example.pokemon.data.model.auth.LoginResponse
import com.example.pokemon.data.model.auth.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun loginUser(email: String, password: String): Flow<LoginResponse>

    fun registerUser(user: User): Flow<RegisterResponse>
}