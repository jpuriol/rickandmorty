package com.example.rickandmorty.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.App
import com.example.rickandmorty.data.mappers.toCharacterInfo
import com.example.rickandmorty.use_cases.edit_character.EditScreen
import com.example.rickandmorty.use_cases.show_characters.CharactersScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val app = context.applicationContext as App

    val characterDAO = app.database.characterDAO()
    val charactersFlow = characterDAO.getAllCharacters()

    val characters by charactersFlow.collectAsState(initial = emptyList())

    NavHost(navController = navController, startDestination = "list") {
        composable(route = "list") {
            CharactersScreen(
                characters = characters.map { it.toCharacterInfo() },
                navigateToDetail = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }
        composable(route = "detail/{id}") {
            val id = it.arguments?.getString("id") ?: return@composable

            EditScreen(
                id = id.toInt(),
                characterDAO = characterDAO,
                back = { navController.popBackStack() },
            )
        }
    }
}