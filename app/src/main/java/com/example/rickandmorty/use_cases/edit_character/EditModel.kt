package com.example.rickandmorty.use_cases.edit_character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.model.CharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditModel(
    characterID: Int,
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val _state = MutableStateFlow(Character())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val initial = charactersRepository.character(characterID)
            _state.update { initial }
        }
    }

    fun setName(name: String) {
        _state.update { it.copy(name = name) }
    }

    fun setStatus(status: String) {
        _state.update { it.copy(status = status) }
    }

    fun setSpecies(species: String) {
        _state.update { it.copy(species = species) }
    }

    fun setLocation(location: String) {
        _state.update { it.copy(location = location) }
    }

    fun save() {
        viewModelScope.launch {
            charactersRepository.update(state.value)
        }
    }
}