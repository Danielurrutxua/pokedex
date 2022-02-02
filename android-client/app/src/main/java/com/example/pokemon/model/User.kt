package com.example.pokemon.model

import java.util.*

data class User (
    val id: UUID,
    val username: String,
    val email: String,
    val password: String
)