package com.example.pokemon.data.source.user

import com.example.pokemon.data.model.User
import com.example.pokemon.data.model.auth.LoginResponse
import com.example.pokemon.data.model.auth.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @POST("user/login/{email}/{password}")
    fun loginUser(
        @Query("email") email: String,
        @Query("password") password: String
    ): Call<LoginResponse>

    @POST("user")
    fun registerUser(@Body user: User): Call<Unit>
}