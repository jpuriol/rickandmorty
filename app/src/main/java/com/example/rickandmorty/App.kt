package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.data.local.CharacterDatabase

class App : Application() {

    companion object {
        private lateinit var instance_: App
        val instance by lazy { instance_ }
    }

    lateinit var database: CharacterDatabase

    override fun onCreate() {
        instance_ = this
        super.onCreate()
        database = CharacterDatabase.getDatabase(this)
    }
}