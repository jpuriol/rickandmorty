package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.data.local.CharacterDatabase

class App : Application() {

    lateinit var database: CharacterDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = CharacterDatabase.getDatabase(this)

        /*

        val scope = MainScope()
        scope.launch(Dispatchers.IO) {
            var i = 0
            while (isActive) {
                Log.i("App", "Hello from MainScope, ${i++}")
                delay(1_000)
            }
        }

         */
    }
}