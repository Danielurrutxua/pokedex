package com.example.pokemon.pokeapi.datasource.service

import com.example.pokemon.model.Pokemon
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow

class FirebaseServiceDataSource(firebaseFirestore: FirebaseFirestore) :
    PokemonServiceDataSource {
    override fun getPokemon(name: String): Flow<Pokemon> {
        TODO("Not yet implemented")
    }

    override fun getPokemonList(): Flow<List<Pokemon>> {
        TODO("Not yet implemented")
    }

    override fun getPokemonTypes(names: List<String>): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override fun getPokemonAbilities(names: List<String>): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override fun getPokemonListTypes(): Flow<Map<String, List<String>>> {
        TODO("Not yet implemented")
    }

/*
    private val reference = firebaseFirestore.collection("pokemon")

    @ExperimentalCoroutinesApi
    override fun getPokemon(name: String): Flow<Pokemon> {
        return callbackFlow {
            val subscription = reference.document("bulbasaur").addSnapshotListener { snapshot, _ ->
                if (snapshot!!.exists()) {
                    launch {
                        databaseResponseToPokemon(snapshot).collect { pokemon ->
                            offer(pokemon)
                        }
                    }
                }
            }

            awaitClose {
                subscription.remove()
            }
        }
    }


    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    override fun getPokemonList(): Flow<List<Pokemon>> {
        return callbackFlow {

            val subscription = reference.addSnapshotListener { querySnapshot, _ ->
                val pokemonList = mutableListOf<Pokemon>()
                querySnapshot?.forEach { queryDocumentSnapshot ->

                    val pokemon = databaseResponseToPokemon(queryDocumentSnapshot)
                    pokemonList.add(pokemon)
                    offer(pokemonList)
                }
            }
            awaitClose { subscription.remove() }
        }
    }

    @ExperimentalCoroutinesApi
    private fun getAbilities(list: List<DocumentReference>): Flow<List<Ability>> {

        val flows = mutableListOf<Flow<Ability>>()

        list.forEach { reference ->
            flows.add(getAbility(reference))
        }

        return combine(flows) {
            it.toList()
        }

    }

    @ExperimentalCoroutinesApi
    private fun getAbility(abilityReference: DocumentReference): Flow<Ability> {

        return callbackFlow {
            val reference = abilityReference.addSnapshotListener { snapshot, _ ->
                if (snapshot!!.exists()) {
                    val ability = databaseResponseToAbility(snapshot)
                    offer(ability)
                }
            }
            awaitClose {
                reference.remove()
            }
        }
    }


    @ExperimentalCoroutinesApi
    private fun getTypes(list: List<DocumentReference>): Flow<List<Type>> {
        val flows = mutableListOf<Flow<Type>>()

        list.forEach {
            flows.add(getType(it))
        }
        return combine(flows) {
            it.toList()
        }
    }

    @ExperimentalCoroutinesApi
    private fun getType(abilityReference: DocumentReference): Flow<Type> {

        return callbackFlow {
            val reference = abilityReference.addSnapshotListener { snapshot, _ ->
                if (snapshot!!.exists()) {
                    val type = databaseResponseToType(snapshot)
                    offer(type)
                }
            }

            awaitClose {
                reference.remove()
            }
        }
    }

    private fun databaseResponseToPokemon(document: QueryDocumentSnapshot): Pokemon {
        val name = document["name"] as String
        val id = document["id"] as String
        val imgUrl = document["img_url"] as String
        val height = document["height"] as String
        val weight = document["weight"] as String

        return (Pokemon(id, name, imgUrl, height, weight, listOf(), listOf()))

    }

    @ExperimentalCoroutinesApi
    private fun databaseResponseToPokemon(document: DocumentSnapshot): Flow<Pokemon> {
        val name = document["name"] as String
        val id = document["id"] as String
        val imgUrl = document["img_url"] as String
        val height = document["height"] as String
        val weight = document["weight"] as String
        val abilitiesFlow = getAbilities(document["abilities"] as List<DocumentReference>)

        val typesFlow = getTypes(document["types"] as List<DocumentReference>)

        return combine(abilitiesFlow, typesFlow) { abilities, types ->
            Pokemon(id, name, imgUrl, height, weight, abilities, types)
        }

    }

    private fun databaseResponseToAbility(document: DocumentSnapshot): Ability {
        val id = document.id
        val name = document["name"] as String
        val effect = document["effect"] as String
        val shortEffect = document["short_effect"] as String

        return Ability(id, name, effect, shortEffect)
    }

    private fun databaseResponseToType(document: DocumentSnapshot): Type {
        val id = document.id
        val name = document["name"] as String

        return Type(id, name)
    }

 */
}