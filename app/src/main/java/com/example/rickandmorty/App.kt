package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.data.local.CharacterDatabase

class App : Application() {

    lateinit var database: CharacterDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = CharacterDatabase.getDatabase(this)
    }
}