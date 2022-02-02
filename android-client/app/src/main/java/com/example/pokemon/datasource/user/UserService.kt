package com.example.pokemon.datasource.user

import com.example.pokemon.model.User
import com.example.pokemon.model.auth.LoginResponse
import com.example.pokemon.model.auth.RegisterResponse
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @GET("user/login")
    @FormUrlEncoded
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @POST("user")
    @FormUrlEncoded
    fun registerUser(@Body user: User): Response<RegisterResponse>
}