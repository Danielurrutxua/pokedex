package com.example.pokemon.model.realm

import io.realm.RealmList
import io.realm.RealmObject

open class PokemonRealm : RealmObject {

    lateinit var id: String
    lateinit var name: String
    lateinit var imgUrls: RealmList<String>
    lateinit var height: String
    lateinit var weight: String
    lateinit var abilities: RealmList<String>
    lateinit var types: RealmList<String>
    lateinit var stats: RealmList<String>
    var favourite: Boolean = false

    constructor(
        id: String,
        name: String,
        imgUrls: RealmList<String>,
        height: String,
        weight: String,
        abilities: RealmList<String>,
        types: RealmList<String>,
        stats: RealmList<String>,
        favourite: Boolean
    ) {
        this.id = id
        this.name = name
        this.imgUrls = imgUrls
        this.height = height
        this.weight = weight
        this.abilities = abilities
        this.types = types
        this.stats = stats
        this.favourite = favourite

    }

    constructor()
}