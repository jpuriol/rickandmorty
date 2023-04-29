package com.example.rickandmorty.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.ui.components.CharacterDetail
import com.example.rickandmorty.ui.components.CharacterInfo
import com.example.rickandmorty.ui.components.CharactersList

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            CharactersList(
                navigateToDetail = { c ->
                    navController.navigate("detail")
                }
            )
        }
        composable("detail") {
            CharacterDetail(
                character = CharacterInfo(
                    name = "Toxic Rick",
                    imageURL = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
                    status = "Dead",
                    species = "Human",
                    type = "Rick's Toxic Side",
                    origin = "Earth"
                ),
                back = { navController.popBackStack() },
            )
        }
    }
}