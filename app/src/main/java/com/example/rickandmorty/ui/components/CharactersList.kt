package com.example.rickandmorty.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CharactersList(
    characters: List<CharacterInfo>,
    navigateToDetail: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (characters.isEmpty()) {
            item {
                CircularProgressIndicator()
            }
            return@LazyColumn
        }

        items(characters) { character ->
            CharacterSummary(
                character = character,
                onClick = { navigateToDetail(it.id) },
            )
            Divider()
        }

    }
}
