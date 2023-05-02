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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.rickandmorty.R
import com.example.rickandmorty.data.local.CharacterDAO
import com.example.rickandmorty.data.mappers.toCharacteData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetail(
    id: Int,
    characterDAO: CharacterDAO,
    back: () -> Unit,
) {
    val scope = rememberCoroutineScope()

    val model: DetailModel = viewModel(factory = viewModelFactory {
        initializer {
            DetailModel(characterID = id, characterDAO = characterDAO)
        }
    })
    val character by model.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = character.name,
            label = { Text(text = stringResource(R.string.name).uppercase()) },
            onValueChange = { model.setName(it) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = character.status,
            label = { Text(text = stringResource(R.string.status).uppercase()) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = character.species,
            label = { Text(text = stringResource(R.string.species).uppercase()) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = character.type,
            label = { Text(text = stringResource(R.string.type).uppercase()) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = character.gender,
            label = { Text(text = stringResource(R.string.gender).uppercase()) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = character.origin,
            label = { Text(text = stringResource(R.string.origin).uppercase()) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = character.location,
            label = { Text(text = stringResource(R.string.location).uppercase()) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = character.imageURL,
            label = { Text(text = stringResource(R.string.image_url).uppercase()) },
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
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
                onClick = {
                    scope.launch {
                        characterDAO.update(character.toCharacteData())
                    }
                    back()
                },
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
