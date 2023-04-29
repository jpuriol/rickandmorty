package com.example.rickandmorty.remote

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val info: Info,
    val results: List<CharacterResult>
)