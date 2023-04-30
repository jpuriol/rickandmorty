package com.example.rickandmorty.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CharactersList(
    characters: List<CharacterInfo>,
    navigateToDetail: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(characters) { character ->
            CharacterSummary(
                character = character,
                onClick = { navigateToDetail(it.id) },
            )
            Divider()
        }

    }
}
