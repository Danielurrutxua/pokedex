package com.example.pokemon.services.user

import androidx.annotation.VisibleForTesting
import com.example.pokemon.datasource.repositories.user.UserRepository
import com.example.pokemon.datasource.repositories.user.UserRepositoryImpl
import com.example.pokemon.datasource.user.UserDataSource
import com.example.pokemon.datasource.user.UserDataSourceImpl
import com.example.pokemon.datasource.user.UserService
import com.example.pokemon.services.RetrofitBuilder

object UserServiceLocator {
    @Volatile
    var userRepository: UserRepositoryImpl? = null
        @VisibleForTesting set

    fun provideUserRepository(base_url: String): UserRepository {
        synchronized(this) {
            return userRepository ?: createUserRepository(base_url)
        }
    }

    private fun createUserRepository(base_url: String): UserRepository {
        val newRepo = UserRepositoryImpl(createUserDataSource(base_url))
        userRepository = newRepo

        return newRepo
    }

    private fun createUserDataSource(base_url: String): UserDataSource {
        val userService = RetrofitBuilder.build(base_url).create(UserService::class.java)
        return UserDataSourceImpl(userService)
    }
}