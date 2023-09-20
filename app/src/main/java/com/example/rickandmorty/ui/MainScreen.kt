package com.example.rickandmorty.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.App
import com.example.rickandmorty.data.mappers.toCharacterData
import com.example.rickandmorty.data.mappers.toCharacterInfo
import com.example.rickandmorty.data.remote.API
import com.example.rickandmorty.ui.components.CharacterDetail
import com.example.rickandmorty.ui.components.CharactersList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val app = context.applicationContext as App

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

        val f = flow {
            runCatching {
                val page1 = API.getCharacters(page = 1)
                emit(page1.results)

                for (pageNum in 2..page1.info.pages) {
                    val page = API.getCharacters(page = pageNum)
                    emit(page.results)
                }

            }
        }

        scope.launch(Dispatchers.IO) {
            f.collect { characters ->
                for (character in characters) {
                    characterDAO.insert(character.toCharacterData())
                }
            }
        }
    }

}