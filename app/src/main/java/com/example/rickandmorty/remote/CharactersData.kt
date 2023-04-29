package com.example.rickandmorty.remote

import kotlinx.serialization.Serializable

@Serializable
data class CharactersData(
    val info: Info,
    val results: List<Result>
)