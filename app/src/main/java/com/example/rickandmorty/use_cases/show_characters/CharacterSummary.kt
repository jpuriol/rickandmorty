package com.example.rickandmorty.use_cases.show_characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmorty.R
import com.example.rickandmorty.domain.CharacterInfo
import com.example.rickandmorty.ui.theme.RickAndMortyTheme

@Composable
fun CharacterSummary(
    character: CharacterInfo,
    onClick: (c: CharacterInfo) -> Unit,
) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(character) }
        .padding(16.dp)
    ) {
        Text(
            text = character.name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = character.imageURL,
                contentDescription = null,
                placeholder = painterResource(R.drawable.character_placeholder),
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.status).uppercase(),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(
                                when (character.status) {
                                    "Alive" -> Color.Green
                                    "Dead" -> Color.Red
                                    else -> Color.LightGray
                                }
                            )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = character.status,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.species).uppercase(),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = character.species,
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.location).uppercase(),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = character.location,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterSummary() {
    RickAndMortyTheme {
        CharacterSummary(
            character = CharacterInfo(
                id = 0,
                name = "Toxic Rick",
                status = "Dead",
                species = "Human",
                type = "Rick's Toxic Side",
                gender = "Male",
                origin = "Alien Spa",
                location = "Earth",
                imageURL = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
            ),
            onClick = {},
        )
    }
}