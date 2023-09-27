package com.example.rickandmorty

import android.app.Application
import com.example.rickandmorty.data.local.CharacterDatabase
import com.example.rickandmorty.data.mappers.toCharacterData
import com.example.rickandmorty.data.remote.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class App : Application() {

    lateinit var database: CharacterDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = CharacterDatabase.getDatabase(this)


        val scope = MainScope()
        scope.launch(Dispatchers.IO) {

            val results = flow {
                runCatching {
                    val page1 = API.getCharacters(page = 1)
                    emit(page1.results)

                    for (pageNum in 2..page1.info.pages) {
                        val page = API.getCharacters(page = pageNum)
                        emit(page.results)
                    }
                }
            }

            val characterDAO = database.characterDAO()
            results.buffer().collect { characters ->
                for (character in characters) {
                    characterDAO.insert(character.toCharacterData())
                }
            }
        }
    }
}