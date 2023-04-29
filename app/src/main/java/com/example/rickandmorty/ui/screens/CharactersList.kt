package com.example.rickandmorty.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmorty.remote.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CharactersList() {
    val model: CharactersModel = viewModel()
    val state by model.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(state.characters) { character ->
            CharactersItem(character = character)
            Divider()
        }

    }

    val scope = rememberCoroutineScope()
    SideEffect {
        scope.launch(Dispatchers.IO) {
            val charactersPage1 = API.getCharacters(page = 1)

            model.addCharacters(
                charactersPage1.results.map { it.toCharacter() }
            )

            for (page in 2..charactersPage1.info.pages) {
                val charactersPage = API.getCharacters(page)

                model.addCharacters(
                    charactersPage.results.map { it.toCharacter() }
                )
            }
        }
    }
}
