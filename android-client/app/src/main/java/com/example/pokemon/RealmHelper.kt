package com.example.pokemon

import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import java.util.concurrent.Executors


private var realm : Realm? = null

val threadRealm =  Executors.newSingleThreadExecutor().asCoroutineDispatcher()
val coroutineRealm = CoroutineScope(threadRealm)

fun getRealmProMode(block: suspend (realm : Realm) -> Unit) {
    GlobalScope.launch(threadRealm, block = {
        if (realm == null){
            realm = Realm.getInstance(RealmConfiguration.Builder()

                .build())
        }

        realm?.let {
            block.invoke(it)
        }
    })
}

suspend fun getRealm() : Realm {
    if (realm == null) {
        realm = Realm.getInstance(
            RealmConfiguration.Builder()
                .build()
        )
    }
    return realm!!
}


fun <T>Flow<T>.launchRealm() : Flow<T>{
    return flowOn(threadRealm)
}

fun <T>Flow<T>.launchUI() : Flow<T>{
    launchIn(MainScope())
    return this
}

fun <T>Flow<T>.launchGlobal() : Flow<T>{
    launchIn(GlobalScope)
    return this
}

fun showInUIThread(block: suspend CoroutineScope.() -> Unit) {
    GlobalScope.launch(Dispatchers.Main,block = block)
}
