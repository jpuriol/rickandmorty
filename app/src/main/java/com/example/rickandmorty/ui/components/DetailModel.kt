package com.example.rickandmorty.ui.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.local.CharacterDAO
import com.example.rickandmorty.data.mappers.toCharacterInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailModel(characterID: Int, characterDAO: CharacterDAO) : ViewModel() {

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


}