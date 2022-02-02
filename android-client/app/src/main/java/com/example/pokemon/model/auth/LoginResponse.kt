package com.example.pokemon.model.auth

data class LoginResponse (
    val code: Int,
    val message: String,
    val username: String,
    val email: String
        )