package com.example.rickandmorty.use_cases.edit_character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.local.CharacterDAO
import com.example.rickandmorty.data.mappers.toCharacterData
import com.example.rickandmorty.data.mappers.toCharacterInfo
import com.example.rickandmorty.domain.CharacterInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditModel(
    characterID: Int,
    private val characterDAO: CharacterDAO
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterInfo())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val initial = characterDAO.getCharacter(characterID).first()
            _state.update { initial.toCharacterInfo() }
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
            val character = state.value.toCharacterData()
            characterDAO.update(character)
        }
    }
}