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
import kotlinx.coroutines.launch

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

            val detail by characterDAO.getCharacter(id.toInt())
                .collectAsState(initial = CharacterData())

            CharacterDetail(
                initial = detail.toCharacterInfo(),
                characterDAO = characterDAO,
                back = { navController.popBackStack() },
            )
        }
    }


    val scope = rememberCoroutineScope()
    SideEffect {
        scope.launch(Dispatchers.IO) {
            runCatching {
                val charactersPage1 = API.getCharacters(page = 1)

                for (character in charactersPage1.results) {
                    characterDAO.insert(character.toCharacterData())
                }

                for (page in 2..charactersPage1.info.pages) {
                    val charactersPage = API.getCharacters(page)

                    for (character in charactersPage.results) {
                        characterDAO.insert(character.toCharacterData())
                    }
                }
            }
        }
    }

}