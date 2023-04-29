package com.example.rickandmorty.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmorty.remote.API
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CharactersScreen() {
    val model: CharactersModel = viewModel()
    val state by model.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(state.characters) { character ->
            Spacer(modifier = Modifier.height(8.dp))
            CharactersItem(character = character)
            Divider()
        }

    }

    val scope = rememberCoroutineScope()
    SideEffect {
        scope.launch(Dispatchers.IO) {
            val charactersData = API.getCharcaters()

            Log.i("api_rest", "results size: ${charactersData.results.size}")

            val characters = charactersData.results.map {
                Character(
                    name = it.name,
                    imageURL = it.image,
                    status = it.status,
                    species = it.species,
                    origin = it.origin.name,
                )
            }

            model.setCharecters(characters = characters)

        }
    }
}
