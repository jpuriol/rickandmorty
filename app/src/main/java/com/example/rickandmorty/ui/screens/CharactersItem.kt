package com.example.rickandmorty.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.theme.RickAndMortyTheme

@Composable
fun CharactersItem(character: Character) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                /* TODO */
            }
            .padding(
                top = 32.dp,
                bottom = 32.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        AsyncImage(
            model = character.imageURL,
            contentDescription = null,
            placeholder = painterResource(R.drawable.character_placeholder),
            modifier = Modifier
                .clip(RoundedCornerShape(2.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.status_x, character.status),
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = stringResource(R.string.species_x, character.species),
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = stringResource(R.string.origin_x, character.origin),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharactersItems() {
    RickAndMortyTheme {
        CharactersItem(
            character = Character(
                name = "Rick Sanchez",
                imageURL = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                status = "Alive",
                species = "Human",
                origin = "Earth"
            )
        )
    }
}