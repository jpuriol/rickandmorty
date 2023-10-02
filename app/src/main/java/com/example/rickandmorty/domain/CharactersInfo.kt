package com.example.rickandmorty.domain


data class CharacterInfo(
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val origin: String = "",
    val location: String = "",
    val imageURL: String = "",
)