package com.example.rickandmorty.ui.components

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CharactersState(
    val characters: List<CharacterInfo> = emptyList()
)

data class CharacterInfo(
    val name: String,
    val imageURL: String,
    val status: String,
    val species: String,
    val type: String,
    val origin: String,
)

class CharactersModel : ViewModel() {
    private val _state = MutableStateFlow(CharactersState())
    val state = _state.asStateFlow()

    fun addCharacters(characters: List<CharacterInfo>) {
        _state.update {
            it.copy(characters = it.characters + characters)
        }
    }

}