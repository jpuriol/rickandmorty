package com.example.rickandmorty.use_cases.show_characters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rickandmorty.model.Character

@Composable
fun CharactersScreen(
    characters: List<Character>,
    navigateToDetail: (id: Int) -> Unit
) {

    if (characters.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        return
    }


    LazyColumn {

        items(characters) { character ->
            CharacterSummary(
                character = character,
                onClick = { navigateToDetail(it.id) },
            )
            Divider()
        }

    }
}
