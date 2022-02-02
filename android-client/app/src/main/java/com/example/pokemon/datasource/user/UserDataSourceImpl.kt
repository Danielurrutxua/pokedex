package com.example.pokemon.datasource.user

import com.example.pokemon.model.User
import com.example.pokemon.model.auth.LoginResponse
import com.example.pokemon.model.auth.RegisterResponse
import kotlinx.coroutines.flow.Flow

class UserDataSourceImpl(private val userService: UserService) : UserDataSource {
    override fun loginUser(email: String, password: String): Flow<LoginResponse> {
        TODO("Not yet implemented")
    }

    override fun registerUser(user: User): Flow<RegisterResponse> {
        TODO("Not yet implemented")
    }
}