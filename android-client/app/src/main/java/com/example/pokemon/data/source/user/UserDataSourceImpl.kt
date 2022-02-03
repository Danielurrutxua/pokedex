package com.example.pokemon.data.source.user

import com.example.pokemon.data.model.User
import com.example.pokemon.data.model.auth.LoginResponse
import com.example.pokemon.data.model.auth.RegisterResponse
import kotlinx.coroutines.flow.Flow

class UserDataSourceImpl(private val userService: UserService) : UserDataSource {
    override fun loginUser(email: String, password: String): Flow<LoginResponse> {
        TODO("Not yet implemented")
    }

    override fun registerUser(user: User): Flow<RegisterResponse> {
        TODO("Not yet implemented")
    }
}