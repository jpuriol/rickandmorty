package com.example.rickandmorty.ui.components

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailModel : ViewModel() {

    private val _state = MutableStateFlow(CharacterInfo())
    val state = _state.asStateFlow()

    fun set(info: CharacterInfo) {
        _state.update { info }
    }

    fun setName(name: String) {
        _state.update { it.copy(name = name) }
    }


}