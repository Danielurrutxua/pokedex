package com.example.pokemon.pokelist

data class Filters(
    var isFav: Boolean = false,
    var isFire: Boolean = false,
    var isPlant: Boolean = false,
    var isPoison: Boolean = false,
    var isFlying: Boolean = false,
    var searchText: String? = "",
    var listOrder: Int = 0
) {


}