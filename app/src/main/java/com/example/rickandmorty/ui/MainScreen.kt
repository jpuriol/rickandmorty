package com.example.rickandmorty.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.App
import com.example.rickandmorty.data.local.CharacterData
import com.example.rickandmorty.data.mappers.toCharacterData
import com.example.rickandmorty.data.mappers.toCharacterInfo
import com.example.rickandmorty.data.remote.API
import com.example.rickandmorty.ui.components.CharacterDetail
import com.example.rickandmorty.ui.components.CharactersList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val app = App.instance

    val characterDAO = app.database.characterDAO()

    val characters by characterDAO.getAllCharacters().collectAsState(initial = emptyList())

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            CharactersList(
                characters = characters.map { it.toCharacterInfo() },
                navigateToDetail = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }
        composable("detail/{id}") {
            val id = it.arguments?.getString("id") ?: return@composable


            CharacterDetail(
                id = id.toInt(),
                characterDAO = characterDAO,
                back = { navController.popBackStack() },
            )
        }
    }


    val scope = rememberCoroutineScope()
    SideEffect {
        val channel = Channel<CharacterData>(BUFFERED)

        scope.launch(Dispatchers.IO) {
            runCatching {
                val charactersPage1 = API.getCharacters(page = 1)

                launch {
                    charactersPage1.results.forEach {
                        channel.send(it.toCharacterData())
                    }
                }

                val deferred = (2..charactersPage1.info.pages).map { page ->
                    async { API.getCharacters(page) }
                }

                deferred.forEach { d ->
                    val response = d.await()
                    response.results.forEach { result ->
                        channel.send(result.toCharacterData())
                    }
                }

                channel.close()
            }
        }

        scope.launch {
            while (true) {
                val resp = channel.receiveCatching()
                if (resp.isSuccess) {
                    val data = resp.getOrThrow()
                    characterDAO.insert(data)
                } else {
                    break
                }
            }
        }

    }

}