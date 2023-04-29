package com.example.rickandmorty.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.R
import com.example.rickandmorty.ui.theme.RickAndMortyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetail(
    character: CharacterInfo,
    back: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = character.name,
            label = { Text(text = stringResource(R.string.name).uppercase()) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = character.status,
            label = { Text(text = stringResource(R.string.status).uppercase()) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = character.species,
            label = { Text(text = stringResource(R.string.species).uppercase()) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = character.type,
            label = { Text(text = stringResource(R.string.type).uppercase()) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = character.gender,
            label = { Text(text = stringResource(R.string.gender).uppercase()) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = character.origin,
            label = { Text(text = stringResource(R.string.origin).uppercase()) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = character.location,
            label = { Text(text = stringResource(R.string.location).uppercase()) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = character.imageURL,
            label = { Text(text = stringResource(R.string.image_url).uppercase()) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { back() },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.back),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                )

            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { back() },
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.save),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                )
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterDetail() {
    RickAndMortyTheme {
        CharacterDetail(
            character = CharacterInfo(
                name = "Toxic Rick",
                status = "Dead",
                species = "Human",
                type = "Rick's Toxic Side",
                gender = "Male",
                origin = "Alien Spa",
                location = "Earth",
                imageURL = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
            ),
            back = {},
        )
    }
}